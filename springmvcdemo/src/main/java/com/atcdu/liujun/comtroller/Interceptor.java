package com.atcdu.liujun.comtroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class Interceptor {
    @RequestMapping("/interceptorTest")
    @ResponseBody
    public String interceptorTest(){
        System.out.println("我是拦截器测试方法");
        return "test success";
    }
}
