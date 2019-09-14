package com.atcdu.liujun.comtroller;

import com.atcdu.liujun.conpoment.CustomException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class ErrorsController {
    @RequestMapping("/TestErrors")
    public String TestErrors(){
        if (true){
            throw new CustomException("你被拒绝登录");
        }
        return "success";
    }

}
