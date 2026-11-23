package com.example.tp1.service;
import com.example.tp1.entity.Article;
import com.example.tp1.entity.Comment;
import com.example.tp1.repository.ArticleRepository;
import com.example.tp1.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public Comment addToArticle(Long articleId, Comment comment) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article non trouvé : " + articleId));
        comment.setArticle(article);
        return commentRepository.save(comment);
    }

    public List<Comment> getByArticle(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    public void delete(Long id) { commentRepository.deleteById(id); }

}
