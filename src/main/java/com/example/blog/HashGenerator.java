package com.example.blog;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 自分のパスワードのハッシュを作るための使い捨てプログラム
public class HashGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode("root");
        System.out.println(hash);
    }
}