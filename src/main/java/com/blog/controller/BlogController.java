package com.blog.controller;

import com.blog.entity.res.univer.DataRespond;
import com.blog.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BlogController {
    private BlogService blogService;
    @GetMapping("/home")
    public DataRespond getHomeData(){
        return blogService.getHomeData();
    }
}
