package com.blog.entity.table;

import lombok.Data;

@Data
public class ArticleTable {
    private Integer id;
    private String name;
    private String outline;
    private Integer typeId;
    private String createDatetime;
}
