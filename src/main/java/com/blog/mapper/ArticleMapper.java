package com.blog.mapper;

import com.blog.entity.table.ArticleTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Select("select id, name, outline, type_id, create_datetime from article " +
            "order by id desc limit 6")
    List<ArticleTable> selectTimelineArticleList();
}
