package com.blog.entity.res.data;

import lombok.Data;

import java.util.List;

@Data
public class ArticleData {
    private Integer id;
    private String name;
    private String outline;
    private List<String> tags;
    private Integer typeId;
    private String createDatetime;
}
