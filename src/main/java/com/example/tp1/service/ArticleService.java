package com.example.tp1.service;


import com.example.tp1.entity.Article;
import com.example.tp1.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAll() { return articleRepository.findAll(); }

    public Article getById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article non trouvé : " + id));
    }
    public Article create(Article article) { return articleRepository.save(article); }

    public Article update(Long id, Article details) {
        Article a = getById(id);
        a.setTitle(details.getTitle());
        a.setContent(details.getContent());
        return articleRepository.save(a);
    }

    public void delete(Long id) { articleRepository.deleteById(id); }
}

