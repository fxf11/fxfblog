package com.fxf.blogbs.utils;

import lombok.Data;

@Data
public class ResultSet {

    private Integer code;
    private String message;

    public ResultSet() {
    }

    public ResultSet(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
