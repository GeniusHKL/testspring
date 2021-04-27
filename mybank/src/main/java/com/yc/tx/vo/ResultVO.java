package com.yc.tx.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class ResultVO<T> implements Serializable {

    private static final  long serializableUID=583929200209944094L;

    private Integer code;
    private T data;
    private String msg;
}
