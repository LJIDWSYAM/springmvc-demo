package com.atcdu.liujun.conpoment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "你被拒绝登录",value = HttpStatus.NOT_ACCEPTABLE)
public class CustomException extends RuntimeException{
    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }
}
