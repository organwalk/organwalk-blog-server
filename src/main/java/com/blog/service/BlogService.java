package com.blog.service;

import com.blog.entity.res.univer.DataRespond;

public interface BlogService {
    DataRespond getHomeData(Integer type);
    DataRespond getSearchData(Integer searchType, String keyword, String info, Integer offset);
}
