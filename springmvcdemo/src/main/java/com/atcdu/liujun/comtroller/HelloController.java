package com.atcdu.liujun.comtroller;

import com.atcdu.liujun.bean.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/index")
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("请求处理中");
        return "hello";
    }
    @RequestMapping("/addEmp")
    public String addEmp(@Valid Employee employee, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            System.out.println("有错误校验");
            FieldError lastNameError = bindingResult.getFieldError("lastName");
            FieldError emailError = bindingResult.getFieldError("email");
            FieldError genderError = bindingResult.getFieldError("gender");
            FieldError birthError = bindingResult.getFieldError("birth");
            if(lastNameError != null) model.addAttribute("lastNameError", lastNameError.getDefaultMessage());
            if(emailError != null) model.addAttribute("emailError", emailError.getDefaultMessage());
            if(genderError != null) model.addAttribute("genderError", genderError.getDefaultMessage());
            if(birthError != null) model.addAttribute("birthError", birthError  .getDefaultMessage());
            return "forward:/index/hello";
        }
        else {
            return "success";
        }
    }
}
