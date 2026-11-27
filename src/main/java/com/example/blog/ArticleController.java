package com.example.blog;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleController(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
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

    @GetMapping("/articles/new")
    public String newForm(Model model) {
        model.addAttribute("articleForm", new ArticleForm());
        return "articles/new";
    }

    @PostMapping("/articles")
    public String create(@Validated @ModelAttribute ArticleForm articleForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "articles/new";
        }

        User author = userRepository.findById(1).orElseThrow(); // 仮実装：ログイン機能ができるまで、投稿者はid 1のユーザーに固定する

        Article article = new Article();
        article.setTitle(articleForm.getTitle());
        article.setBody(articleForm.getBody());
        article.setAuthor(author);
        article.setCreatedAt(LocalDateTime.now());
        article.setUpdatedAt(LocalDateTime.now());
        articleRepository.save(article);

        return "redirect:/articles";
    }
}