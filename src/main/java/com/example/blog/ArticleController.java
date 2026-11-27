package com.example.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/")
    public String top() {
        return "redirect:/articles";
    }

    @GetMapping("/articles")
    public String list(Model model) {
        model.addAttribute("articles", articleRepository.findAllByOrderByCreatedAtDesc());
        return "articles/list";
    }
    
    @GetMapping("/articles/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Article article = articleRepository.findById(id).orElseThrow();
        model.addAttribute("article", article);
        return "articles/detail";
    }
}