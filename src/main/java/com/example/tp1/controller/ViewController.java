package com.example.tp1.controller;

import com.example.tp1.entity.*;
import com.example.tp1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
public class ViewController {
    @Autowired private ProductService productService;
    @Autowired private ArticleService articleService;
    @Autowired private CommentService commentService;
    // Page accueil
    @GetMapping("/")
    public String home() {
        return "index";
    }
    // Page login
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    // ─── PRODUITS ─────────────────────────
    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products";
    }
    @GetMapping("/products/admin")
    public String adminProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "admin-products";
    }
    @PostMapping("/products/admin/add")
    public String addProduct(@RequestParam String name,
                             @RequestParam Double price,
                             @RequestParam String description) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setDescription(description);
        productService.create(p);
        return "redirect:/products/admin";
    }
    @PostMapping("/products/admin/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @RequestParam String name,
                                @RequestParam Double price,
                                @RequestParam String description) {
        Product p = new Product();
        p.setName(name);
        p.setPrice(price);
        p.setDescription(description);
        productService.update(id, p);
        return "redirect:/products/admin";
    }
    @PostMapping("/products/admin/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products/admin";
    }
    @GetMapping("/admin")
    public String adminIndex() {
        return "admin-index";
    }
    // ─── ARTICLES ─────────────────────────
    @GetMapping("/articles")
    public String articles(Model model) {
        model.addAttribute("articles", articleService.getAll());
        return "articles";
    }
    @PostMapping("/articles/{id}/comments")
    public String addComment(@PathVariable Long id,
                             @RequestParam String text,
                             @RequestParam String author) {
        Article article = articleService.getById(id);
        Comment c = new Comment();
        c.setText(text);
        c.setAuthor(author);
        c.setArticle(article);
        commentService.addToArticle(id, c);
        return "redirect:/articles";
    }
    @GetMapping("/articles/admin")
    public String adminArticles(Model model) {
        model.addAttribute("articles", articleService.getAll());
        return "admin-articles";
    }
    @PostMapping("/articles/admin/add")
    public String addArticle(@RequestParam String title,
                             @RequestParam String content) {
        Article a = new Article();
        a.setTitle(title);
        a.setContent(content);
        articleService.create(a);
        return "redirect:/articles/admin";
    }
    @PostMapping("/articles/admin/update/{id}")
    public String updateArticle(@PathVariable Long id,
                                @RequestParam String title,
                                @RequestParam String content) {
        Article a = new Article();
        a.setTitle(title);
        a.setContent(content);
        articleService.update(id, a);
        return "redirect:/articles/admin";
    }
    @PostMapping("/articles/admin/delete/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.delete(id);
        return "redirect:/articles/admin";
    }
}