package com.example.category.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private String code;
    private int status;

    public ErrorResponse(ErrorCode code){
        this.status =code.getStatus();
        this.code =code.getCode();
        this.message =code.getMessage();
    }

    public static ErrorResponse from(ErrorCode code){
        return new ErrorResponse(code);
    }

}
