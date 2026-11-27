package com.example.blog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    // 新しい記事が上に来るように、作成日時の降順で全件を返す
    List<Article> findAllByOrderByCreatedAtDesc();
}