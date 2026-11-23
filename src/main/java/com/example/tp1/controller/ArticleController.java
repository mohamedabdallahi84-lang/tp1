package com.example.tp1.controller;
import com.example.tp1.entity.Article;
import com.example.tp1.entity.Comment;
import com.example.tp1.service.ArticleService;
import com.example.tp1.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Article> getAll() { return articleService.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getById(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Article> create(@RequestBody Article article) {
        return ResponseEntity.status(HttpStatus.CREATED).body(articleService.create(article));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody Article article) {
        return ResponseEntity.ok(articleService.update(id, article));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        articleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long id, @RequestBody Comment comment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addToArticle(id, comment));
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getComments(@PathVariable Long id) {
        return commentService.getByArticle(id);
    }

}
