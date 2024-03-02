package com.blog.service;

import com.blog.entity.res.data.HomeTypeData;
import com.blog.entity.res.univer.DataFailRespond;
import com.blog.entity.res.univer.DataRespond;
import com.blog.entity.res.univer.DataSuccessRespond;
import com.blog.mapper.ArticleMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class BlogServiceImpl implements BlogService{
    private final ArticleMapper articleMapper;
    @Override
    public DataRespond getHomeData(Integer typeId) {
        if (typeId == 0){
            List<HomeTypeData> homeTypeDataList = articleMapper.selectHomeTypeDataTimeLine();
            return homeTypeDataList.isEmpty()
                    ? new DataFailRespond("时间线文章列表为空")
                    : new DataSuccessRespond("ok", homeTypeDataList);
        }
        List<HomeTypeData> homeTypeDataList = articleMapper.selectHomeTypeDataByTypeId(typeId);
        return homeTypeDataList.isEmpty()
                ? new DataFailRespond("当前类别文章列表为空")
                : new DataSuccessRespond("ok", homeTypeDataList);
    }
}
