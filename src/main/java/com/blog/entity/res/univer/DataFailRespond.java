package com.blog.entity.res.univer;

import com.blog.entity.res.code.Code;
import lombok.Data;

/**
 * 通用数据型失败响应
 */
@Data
public class DataFailRespond implements DataRespond {
    private Integer code;
    private String msg;

    public DataFailRespond(String msg) {
        this.code = Code.FAIL;
        this.msg = msg;
    }
}
