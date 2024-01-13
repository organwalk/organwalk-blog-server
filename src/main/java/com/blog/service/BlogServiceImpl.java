package com.blog.service;

import com.blog.entity.res.univer.DataRespond;
import com.blog.entity.res.univer.DataSuccessRespond;
import com.blog.mapper.ArticleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class BlogServiceImpl implements BlogService{
    private final ArticleMapper articleMapper;
    @Override
    public DataRespond getHomeData() {
        return new DataSuccessRespond("success", articleMapper.selectTimelineArticleList());
    }
}
