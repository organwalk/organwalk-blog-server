package com.blog.mapper;

import com.blog.entity.res.data.HomeTypeData;
import com.blog.entity.table.ArticleTable;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Select("select a.id," +
            "  a.name," +
            "  a.outline," +
            "  a.type_id," +
            "  a.create_datetime from article a " +
            "left JOIN article_tags at on a.id = at.a_id " +
            "LEFT JOIN tags t on at.tag_id = t.id where a.type_id = #{typeId} " +
            "GROUP BY a.id, a.name, a.outline, a.type_id, a.create_datetime " +
            "ORDER BY a.create_datetime desc, a.id")
    @Results({
            @Result( id = true, property = "id", column = "id"),
            @Result( property = "name", column = "name"),
            @Result( property = "outline", column = "outline"),
            @Result( property = "tags", column = "id", javaType = List.class, many = @Many(select = "com.blog.mapper.TagsMapper.selectTagsListByAId")),
            @Result( property = "typeId", column = "type_id"),
            @Result( property = "createDatetime", column = "create_datetime"),
    })
    List<HomeTypeData> selectHomeTypeDataByTypeId(Integer typeId);

    @Select("select a.id," +
            "  a.name," +
            "  a.outline," +
            "  a.type_id," +
            "  a.create_datetime from article a " +
            "left JOIN article_tags at on a.id = at.a_id " +
            "LEFT JOIN tags t on at.tag_id = t.id " +
            "GROUP BY a.id, a.name, a.outline, a.type_id, a.create_datetime " +
            "ORDER BY a.create_datetime desc, a.id")
    @Results({
            @Result( id = true, property = "id", column = "id"),
            @Result( property = "name", column = "name"),
            @Result( property = "outline", column = "outline"),
            @Result( property = "tags", column = "id", javaType = List.class, many = @Many(select = "com.blog.mapper.TagsMapper.selectTagsListByAId")),
            @Result( property = "typeId", column = "type_id"),
            @Result( property = "createDatetime", column = "create_datetime"),
    })
    List<HomeTypeData> selectHomeTypeDataTimeLine();
}
