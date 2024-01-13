package com.blog.entity.res.univer;

import com.blog.entity.res.code.Code;
import lombok.Data;

@Data
public class DataPagingSuccessRespond implements DataRespond{
    private Integer code;
    private String msg;
    private Integer total;
    private Object data;

    public DataPagingSuccessRespond(String msg, Integer total, Object data) {
        this.code = Code.SUCCESS;
        this.msg = msg;
        this.total = total;
        this.data = data;
    }
}
