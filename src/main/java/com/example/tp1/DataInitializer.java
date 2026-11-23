package com.example.tp1;
import lombok.*;
import com.example.tp1.entity.Article;
import com.example.tp1.entity.Comment;
import com.example.tp1.entity.Product;
import com.example.tp1.repository.ArticleRepository;
import com.example.tp1.repository.CommentRepository;
import com.example.tp1.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataInitializer {

    @Bean
    @Profile("dev")
    public CommandLineRunner initData(
            ProductRepository productRepo,
            ArticleRepository articleRepo,
            CommentRepository commentRepo) {

        return args -> {

            Product p1 = new Product();
            p1.setName("Laptop");
            p1.setPrice(799.99);
            p1.setDescription("Ordinateur 16GB RAM");
            productRepo.save(p1);

            Product p2 = new Product();
            p2.setName("Souris");
            p2.setPrice(29.99);
            p2.setDescription("Souris sans fil");
            productRepo.save(p2);

            Article a1 = new Article();
            a1.setTitle("Introduction a Spring Boot");
            a1.setContent("Spring Boot simplifie le developpement");
            articleRepo.save(a1);

            Article a2 = new Article();
            a2.setTitle("Les relations JPA");
            a2.setContent("OneToMany et ManyToOne");
            articleRepo.save(a2);

            Comment c1 = new Comment();
            c1.setText("Super article");
            c1.setAuthor("Alice");
            c1.setArticle(a1);
            commentRepo.save(c1);

            Comment c2 = new Comment();
            c2.setText("Tres utile merci");
            c2.setAuthor("Bob");
            c2.setArticle(a1);
            commentRepo.save(c2);

            Comment c3 = new Comment();
            c3.setText("Bon tuto sur JPA");
            c3.setAuthor("Charlie");
            c3.setArticle(a2);
            commentRepo.save(c3);
        };
    }
}