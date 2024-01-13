package com.blog.entity.res.univer;

import com.blog.entity.res.code.Code;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MsgRes {
    private Integer code;
    private String msg;

    public MsgRes success(String msg){
        return MsgRes.builder()
                .code(Code.SUCCESS)
                .msg(msg)
                .build();
    }

    public MsgRes fail(String msg){
        return MsgRes.builder()
                .code(Code.FAIL)
                .msg(msg)
                .build();
    }

    public MsgRes unauthorized(String msg){
        return MsgRes.builder()
                .code(Code.UNAUTHORIZED)
                .msg(msg)
                .build();
    }
}
