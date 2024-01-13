package com.blog.entity.res.univer;

import com.blog.entity.res.code.Code;
import lombok.Data;

@Data
public class DataUnAuthorized implements DataRespond{
    private Integer code;
    private String msg;

    public DataUnAuthorized(String msg) {
        this.code = Code.UNAUTHORIZED;
        this.msg = msg;
    }
}
