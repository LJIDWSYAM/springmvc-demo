package com.atcdu.liujun.conpoment;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class CenterException {
    @ExceptionHandler(Exception.class)
    public ModelAndView handelException(Exception ex){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("MyErrors");
        mv.addObject("msg",ex.getMessage());
        return mv;
    }

}
