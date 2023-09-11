package com.example.category.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    CATEGORY_NOT_FOUND_CODE(400, "C001", ".");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status,String code,String message){
        this.status =status;
        this.code =code;
        this.message =message;
    }

}
