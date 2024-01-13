package com.blog.entity.res.univer;

import com.blog.entity.res.code.Code;
import lombok.Data;

/**
 * 通用数据型成功响应
 */
@Data
public class DataSuccessRespond implements DataRespond {
    private Integer code;
    private String msg;
    private Object data;

    public DataSuccessRespond(String msg, Object data) {
        this.code = Code.SUCCESS;
        this.msg = msg;
        this.data = data;
    }
}
