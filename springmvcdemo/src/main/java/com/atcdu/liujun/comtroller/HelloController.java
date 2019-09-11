package com.atcdu.liujun.comtroller;

import com.atcdu.liujun.bean.Employee;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.PortUnreachableException;
import java.security.PublicKey;

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

    @RequestMapping("/testJson")
    @ResponseBody
    public String testJson(){
        return "json test success";
    }
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(Model model,@RequestParam("fileTarget") MultipartFile multipartFile) throws IOException {

        System.out.println(multipartFile.getName());
        System.out.println(multipartFile.getOriginalFilename());
        multipartFile.transferTo(new File(
                "C:\\Users\\liujun\\Desktop\\java-demo" +
                        "\\springmvcdemo\\src\\main\\webapp\\images\\"+multipartFile.getOriginalFilename()
                ));
        model.addAttribute("msg","文件上传成功");
        return "Success";
    }
}
