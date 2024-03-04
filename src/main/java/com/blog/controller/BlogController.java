package com.blog.controller;

import com.blog.entity.res.univer.DataFailRespond;
import com.blog.entity.res.univer.DataRespond;
import com.blog.service.BlogService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Objects;

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

    @GetMapping("/articles/{type}/{keyword}/{info}/{offset}")
    public DataRespond getSearchData(@PathVariable
                                     @Min(value = 1, message = "path的第一个参数type只能为1、2或3")
                                     @Max(value = 3, message = "path的第一个参数type只能为1、2或3")
                                     Integer type,
                                     @PathVariable
                                     @NotNull(message = "path的第二个参数keyword不能为空")
                                     @Length(message = "path的第二个参数keyword长度不能超过30个字符")
                                     String keyword,
                                     @PathVariable
                                     @NotNull(message = "path的第三个参数info不能为空")
                                     @Length(message = "path的第三个参数info长度不能超过30个字符")
                                     String info,
                                     @PathVariable
                                     @Min(value = 0, message = "path的第四个参数offset必须为正整数，包含0")
                                     Integer offset) {
        if (type == 1 && !Objects.equals(info, "none")) {
            return new DataFailRespond("当type为1时，info其值必须为none");
        } else if (type == 3 && !Arrays.asList("1", "2", "3").contains(info)) {
            return new DataFailRespond("当type为3时，info其值必须为1、2或3");
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return blogService.getSearchData(type, keyword, info, offset);
        }
    }

    @GetMapping("/articles/type/{type}/{offset}")
    public DataRespond getTypeArticleData(@PathVariable
                                          @Min(value = 1, message = "path的第一个参数type只能为1、2或3")
                                          @Max(value = 3, message = "path的第一个参数type只能为1、2或3")
                                          Integer type,
                                          @PathVariable
                                          @Min(value = 0, message = "path的第二个参数offset必须为正整数，包含0")
                                          Integer offset){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return blogService.getTypeArticleData(type, offset);
    }

    @GetMapping("/articles/type/tags/{type}")
    public DataRespond getTagsArticleCountByType(@PathVariable
                                                     @Min(value = 1, message = "path的第一个参数type只能为1、2或3")
                                                     @Max(value = 3, message = "path的第一个参数type只能为1、2或3")
                                                     Integer type){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return blogService.getTagsArticleCountByType(type);
    }

    @GetMapping("/articles/tag/{tagName}/{offset}")
    public DataRespond getTagArticleData(@PathVariable
                                          @NotBlank(message = "path的第一个参数tagName不能为空")
                                          String tagName,
                                          @PathVariable
                                          @Min(value = 0, message = "path的第二个参数offset必须为正整数，包含0")
                                          Integer offset){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return blogService.getTagArticleData(tagName, offset);
    }
}
