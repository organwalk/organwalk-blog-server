package com.blog.controller;

import com.blog.entity.res.univer.DataRespond;
import com.blog.service.BlogService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Validated
@CrossOrigin
public class BlogController {
    private BlogService blogService;

    @GetMapping("/home/{typeId}")
    public DataRespond getHomeData(@PathVariable
                                   @Min(value = 0, message = "typeId只能为0、1、2或3")
                                   @Max(value = 3, message = "typeId只能为0、1、2或3")
                                   Integer typeId) {
        return blogService.getHomeData(typeId);
    }
}
