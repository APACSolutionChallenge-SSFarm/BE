// 테스트용 컨트롤러입니다 :)
package com.example.sungshinfarm.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello!";
    }
}