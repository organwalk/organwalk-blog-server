package com.blog.service;

import com.blog.entity.res.data.ArticleData;
import com.blog.entity.res.data.TagsData;
import com.blog.entity.res.univer.DataFailRespond;
import com.blog.entity.res.univer.DataPagingSuccessRespond;
import com.blog.entity.res.univer.DataRespond;
import com.blog.entity.res.univer.DataSuccessRespond;
import com.blog.mapper.ArticleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
public class BlogServiceImpl implements BlogService {
    private final ArticleMapper articleMapper;

    @Override
    public DataRespond getHomeData(Integer typeId) {
        if (typeId == 0) {
            List<ArticleData> articleDataList = articleMapper.selectHomeTypeDataTimeLine();
            return articleDataList.isEmpty()
                    ? new DataFailRespond("文章列表为空")
                    : new DataSuccessRespond("ok", articleDataList);
        }
        List<ArticleData> articleDataList = articleMapper.selectHomeTypeDataByTypeId(typeId);
        return articleDataList.isEmpty()
                ? new DataFailRespond("文章列表为空")
                : new DataSuccessRespond("ok", articleDataList);
    }

    /**
     * 获取查询数据
     *
     * @param searchType 1.全部 2.指定标签 3.指定类别
     * @return DataRespond
     */
    @Override
    public DataRespond getSearchData(Integer searchType, String keyword, String info, Integer offset) {
        List<ArticleData> articleDataList = new ArrayList<>();
        Integer sum = 0;
        switch (searchType) {
            case 1 -> {
                sum = articleMapper.countAllArticleAboutNameAndOutlineByKeyword(keyword);
                if (Objects.isNull(sum) || sum == 0) return new DataFailRespond("查询结果为空");
                articleDataList = articleMapper.selectAllArticleAboutNameAndOutlineByKeyword(keyword, offset);
            }
            case 2 -> {
                sum = articleMapper.countAllArticleAboutNameAndOutlineByTagAndKeyword(info, keyword);
                if (Objects.isNull(sum) || sum == 0) return new DataFailRespond("查询结果为空");
                articleDataList = articleMapper.selectAllArticleAboutNameAndOutlineByTagAndKeyword(info, keyword, offset);
            }
            case 3 -> {
                sum = articleMapper.countAllArticleAboutNameAndOutlineByTypeAndKeyword(Integer.parseInt(info), keyword);
                if (Objects.isNull(sum) || sum == 0) return new DataFailRespond("查询结果为空");
                articleDataList = articleMapper.selectAllArticleAboutNameAndOutlineByTypeAndKeyword(Integer.parseInt(info), keyword, offset);
            }
        }
        return articleDataList.isEmpty()
                ? new DataFailRespond("查询结果为空")
                : new DataPagingSuccessRespond("ok", sum, articleDataList);
    }

    @Override
    public DataRespond getTypeArticleData(Integer type, Integer offset) {
        Integer sum = articleMapper.countAllArticleByType(type);
        if (Objects.isNull(sum) || sum == 0) return new DataFailRespond("文章列表为空");
        List<ArticleData> articleDataList = articleMapper.selectAllArticleByType(type, offset);
        return articleDataList.isEmpty()
                ? new DataFailRespond("已获取所有")
                : new DataPagingSuccessRespond("ok", sum, articleDataList);
    }

    @Override
    public DataRespond getTagsArticleCountByType(Integer type) {
        List<TagsData> tagsDataList = articleMapper.selectTagsArticleCountByType(type);
        return tagsDataList.isEmpty()
                ? new DataFailRespond("标签列表为空")
                : new DataSuccessRespond("ok", tagsDataList);
    }

    @Override
    public DataRespond getTagArticleData(String tagName, Integer offset) {
        Integer sum = articleMapper.countAllArticleByTagName(tagName);
        if (Objects.isNull(sum) || sum == 0) return new DataFailRespond("文章列表为空");
        List<ArticleData> articleDataList = articleMapper.selectAllArticleByTagName(tagName, offset);
        return articleDataList.isEmpty()
                ? new DataFailRespond("已获取所有")
                : new DataPagingSuccessRespond("ok", sum, articleDataList);
    }
}
