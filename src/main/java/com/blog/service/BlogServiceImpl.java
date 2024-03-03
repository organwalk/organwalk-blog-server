package com.blog.service;

import com.blog.entity.res.data.ArticleData;
import com.blog.entity.res.univer.DataFailRespond;
import com.blog.entity.res.univer.DataRespond;
import com.blog.entity.res.univer.DataSuccessRespond;
import com.blog.mapper.ArticleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class BlogServiceImpl implements BlogService{
    private final ArticleMapper articleMapper;
    @Override
    public DataRespond getHomeData(Integer typeId) {
        if (typeId == 0){
            List<ArticleData> articleDataList = articleMapper.selectHomeTypeDataTimeLine();
            return articleDataList.isEmpty()
                    ? new DataFailRespond("时间线文章列表为空")
                    : new DataSuccessRespond("ok", articleDataList);
        }
        List<ArticleData> articleDataList = articleMapper.selectHomeTypeDataByTypeId(typeId);
        return articleDataList.isEmpty()
                ? new DataFailRespond("当前类别文章列表为空")
                : new DataSuccessRespond("ok", articleDataList);
    }

    /**
     * 获取查询数据
     * @param searchType 1.全部 2.指定标签 3.指定类别
     * @return DataRespond
     */
    @Override
    public DataRespond getSearchData(Integer searchType, String keyword, String info, Integer offset) {
        List<ArticleData> articleDataList = new ArrayList<>();
        switch (searchType){
            case 1 -> articleDataList = articleMapper.selectAllArticleAboutNameAndOutlineByKeyword(keyword, offset);
            case 2 -> articleDataList = articleMapper.selectAllArticleAboutNameAndOutlineByTagAndKeyword(info, keyword, offset);
            case 3 -> articleDataList = articleMapper.selectAllArticleAboutNameAndOutlineByTypeAndKeyword(Integer.parseInt(info), keyword, offset);
        }
        return articleDataList.isEmpty()
                ? new DataFailRespond("查询结果为空")
                : new DataSuccessRespond("ok", articleDataList);
    }
}
