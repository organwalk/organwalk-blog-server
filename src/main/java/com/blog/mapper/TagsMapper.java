package com.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagsMapper {
    @Select("select t.name as name from tags t LEFT JOIN article_tags at on t.id = at.tag_id where at.a_id = #{aId} ORDER BY LENGTH(t.name)")
    List<String> selectTagsListByAId(Integer aId);
}
