package com.blog.mapper;

import com.blog.entity.res.data.ArticleData;
import com.blog.entity.res.data.TagsData;
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
            "ORDER BY a.create_datetime desc, a.id limit 6")
    @Results(id = "ArticleData", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "outline", column = "outline"),
            @Result(property = "tags", column = "id", javaType = List.class, many = @Many(select = "com.blog.mapper.TagsMapper.selectTagsListByAId")),
            @Result(property = "typeId", column = "type_id"),
            @Result(property = "createDatetime", column = "create_datetime"),
    })
    List<ArticleData> selectHomeTypeDataByTypeId(Integer typeId);

    @Select("select a.id," +
            "  a.name," +
            "  a.outline," +
            "  a.type_id," +
            "  a.create_datetime from article a " +
            "left JOIN article_tags at on a.id = at.a_id " +
            "LEFT JOIN tags t on at.tag_id = t.id " +
            "GROUP BY a.id, a.name, a.outline, a.type_id, a.create_datetime " +
            "ORDER BY a.create_datetime desc, a.id limit 6")
    @ResultMap("ArticleData")
    List<ArticleData> selectHomeTypeDataTimeLine();

    @Select("select count(a.id) from article a " +
            "WHERE a.name like CONCAT('%', #{keyword}, '%') or a.outline like CONCAT('%', #{keyword}, '%')")
    Integer countAllArticleAboutNameAndOutlineByKeyword(@Param("keyword") String keyword);

    @Select("select a.id," +
            "  a.name," +
            "  a.outline," +
            "  a.type_id," +
            "  a.create_datetime from article a " +
            "LEFT JOIN article_tags at on a.id = at.a_id " +
            "LEFT JOIN tags t on at.tag_id = t.id " +
            "WHERE a.name like CONCAT('%', #{keyword}, '%') or a.outline like CONCAT('%', #{keyword}, '%')" +
            "GROUP BY a.id, a.name, a.outline, a.type_id, a.create_datetime " +
            "ORDER BY a.create_datetime desc, a.id limit 6 offset #{offset}")
    @ResultMap("ArticleData")
    List<ArticleData> selectAllArticleAboutNameAndOutlineByKeyword(@Param("keyword") String keyword,
                                                                   @Param("offset") Integer offset);

    @Select("select count(a.id) from article a " +
            "LEFT JOIN article_tags at on a.id = at.a_id " +
            "LEFT JOIN tags t on at.tag_id = t.id " +
            "WHERE (a.name like CONCAT('%', #{keyword}, '%') or a.outline like CONCAT('%', #{keyword}, '%')) " +
            "and t.name = #{tag} ")
    Integer countAllArticleAboutNameAndOutlineByTagAndKeyword(@Param("tag") String tag,
                                                              @Param("keyword") String keyword);

    @Select("select a.id," +
            "  a.name," +
            "  a.outline," +
            "  a.type_id," +
            "  a.create_datetime from article a " +
            "LEFT JOIN article_tags at on a.id = at.a_id " +
            "LEFT JOIN tags t on at.tag_id = t.id " +
            "WHERE (a.name like CONCAT('%', #{keyword}, '%') or a.outline like CONCAT('%', #{keyword}, '%')) " +
            "and t.name = #{tag} " +
            "GROUP BY a.id, a.name, a.outline, a.type_id, a.create_datetime " +
            "ORDER BY a.create_datetime desc, a.id limit 6 offset #{offset}")
    @ResultMap("ArticleData")
    List<ArticleData> selectAllArticleAboutNameAndOutlineByTagAndKeyword(@Param("tag") String tag,
                                                                         @Param("keyword") String keyword,
                                                                         @Param("offset") Integer offset);

    @Select("select count(a.id) from article a " +
            "WHERE (a.name like CONCAT('%', #{keyword}, '%') or a.outline like CONCAT('%', #{keyword}, '%')) " +
            "and a.type_id = #{type}")
    Integer countAllArticleAboutNameAndOutlineByTypeAndKeyword(@Param("type") Integer type,
                                                               @Param("keyword") String keyword);

    @Select("select a.id," +
            "  a.name," +
            "  a.outline," +
            "  a.type_id," +
            "  a.create_datetime from article a " +
            "LEFT JOIN article_tags at on a.id = at.a_id " +
            "LEFT JOIN tags t on at.tag_id = t.id " +
            "WHERE (a.name like CONCAT('%', #{keyword}, '%') or a.outline like CONCAT('%', #{keyword}, '%')) " +
            "and a.type_id = #{type} " +
            "GROUP BY a.id, a.name, a.outline, a.type_id, a.create_datetime " +
            "ORDER BY a.create_datetime desc, a.id limit 6 offset #{offset}")
    @ResultMap("ArticleData")
    List<ArticleData> selectAllArticleAboutNameAndOutlineByTypeAndKeyword(@Param("type") Integer type,
                                                                          @Param("keyword") String keyword,
                                                                          @Param("offset") Integer offset);

    @Select("select count(id) from article " +
            "WHERE type_id = #{type} ")
    Integer countAllArticleByType(@Param("type") Integer type);

    @Select("select a.id," +
            "  a.name," +
            "  a.outline," +
            "  a.type_id," +
            "  a.create_datetime from article a " +
            "LEFT JOIN article_tags at on a.id = at.a_id " +
            "LEFT JOIN tags t on at.tag_id = t.id " +
            "WHERE a.type_id = #{type} " +
            "GROUP BY a.id, a.name, a.outline, a.type_id, a.create_datetime " +
            "ORDER BY a.create_datetime desc, a.id limit 6 offset #{offset}")
    @ResultMap("ArticleData")
    List<ArticleData> selectAllArticleByType(@Param("type") Integer type,
                                             @Param("offset") Integer offset);

    @Select("SELECT t.name, COUNT(at.a_id) AS count " +
            "FROM tags t " +
            "LEFT JOIN article_tags at ON t.id = at.tag_id " +
            "LEFT JOIN article a ON at.a_id = a.id " +
            "where a.type_id = #{type} " +
            "GROUP BY t.id order by count desc")
    List<TagsData> selectTagsArticleCountByType(Integer type);

    @Select("select count(a.id) from article a " +
            "LEFT JOIN article_tags at on a.id = at.a_id " +
            "LEFT JOIN tags t on at.tag_id = t.id " +
            "WHERE t.name = #{tagName} ")
    Integer countAllArticleByTagName(@Param("tagName") String tagName);

    @Select("select a.id," +
            "  a.name," +
            "  a.outline," +
            "  a.type_id," +
            "  a.create_datetime from article a " +
            "LEFT JOIN article_tags at on a.id = at.a_id " +
            "LEFT JOIN tags t on at.tag_id = t.id " +
            "WHERE t.name = #{tagName} " +
            "GROUP BY a.id, a.name, a.outline, a.type_id, a.create_datetime " +
            "ORDER BY a.create_datetime desc, a.id limit 6 offset #{offset}")
    @ResultMap("ArticleData")
    List<ArticleData> selectAllArticleByTagName(@Param("tagName") String tagName,
                                             @Param("offset") Integer offset);
}
