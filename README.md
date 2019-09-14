# springmvc  理解

## 一。 先在web.xml中配置springmvc的主键

```xml


<!--DispatcherServlet是调度器-->
    <servlet>
        <servlet-name>springDispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:springMvc-config.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springDispatcherServlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
```   
 配置springmvc的调度器，在调度器中加入对spring xml文件的解析
 <load-on-startup>1</load-on-startup>配置最高加载级别   
 == 注意dispatcher 是一个servlet标签包裹着，所以它是一个servlet ==
 
 
 
 ## SpringMvc的文件上传  
### 第一步：配置maven的pom.xml文件，因为文件上传需要依赖两个jar包
（commons-fileupload和commons-io）
```xml
<!--文件上传依赖的两个jar包-->
    <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.3.1</version>
    </dependency>
 
    <dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>2.4</version>
    </dependency>
```
### 第二步：在spring-web.xml中配置多媒体处理器
```xml
<!-- 配置多媒体处理器 -->
    <!-- 注意：这里id必须填写：multipartResolver -->
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <!-- 最大上传文件大小 -->
        <property name="maxUploadSize" value="8388608" />
        <!--设置编码-->
        <property name="defaultEncoding" value="UTF-8"></property>
    </bean>
```
### 第三步：编写一个简单的前端页面，如：fileupdate.jsp
```html
<table>
     <thead>
        <h4>${msg}</h4>
     </thead>
     <tbody>
       <tr>
          <form action="/picUpload" enctype="multipart/form-data" method="post">
             <td><input type="file" name="pictureFile"></td>
             <td><input type="submit"></td>
          </form>
       </tr>
     </tbody>
</table>
```

### 第四步：编写一个controller控制器来接收图片

```java
/**
     * 接收上传的文件并保存到文件中
     * @return
     */
    @RequestMapping(value = "picUpload",method = RequestMethod.POST)
    public String pictureUpload(MultipartFile pictureFile, Model model) throws IOException {
 
            // 图片新名字
            String newName = UUID.randomUUID().toString();
            // 图片原来的名字
            String oldName = pictureFile.getOriginalFilename();
            // 后缀
            String sux = oldName.substring(oldName.lastIndexOf("."));
             //新建本地文件流
            File file = new File("D:\\SSMBasic\\src\\main\\webapp\\WEB-INF\\img\\" + newName + sux);
            // 写入本地磁盘
            pictureFile.transferTo(file);
 
            model.addAttribute("msg","上传成功");
        return "fileupload";
    }
 
```   
## SpringMVC国际化  
    
        <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
            <!--  basename指定基础名-->
            <property name="basename" value="i18n"></property>
        </bean>
    
    
        1、导包导入了jstl的时候会自动创建为一个jstlView；可以快速方便的支持国际化功能；
        2、可以支持快速国际化；
        1）、javaWeb国际化步骤；
        1）、得得到一个Locale对象；
        2）、使用ResourceBundle绑定国际化资源文件；
        3）、使用ResourceBundle.getString("key")；获取到国际化配置文件中的值；
        4）、web页面的国际化，fmt标签库来做；
        <fmt:setLocale>
        <fmt:setBundle >
        <fmt:message>
        2）、有了JstlView以后；
        1）、让Spring管理国际化资源就行
    
        <!--让SpringMVC管理国际化资源文件；配置一个资源文件管理器  -->
        <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
            <!--  basename指定基础名-->
            <property name="basename" value="i18n"></property>value为国际化文件的前缀
        </bean>
    
        2）、直接去页面使用<fmt:message>；
    
        <fmt:message key="welcomeinfo"/>
    </h1>
        <form action="">
            <fmt:message key="username"/>:<input /><br/>
            <fmt:message key="password"/>:<input /><br/>
            <input type="submit" value='<fmt:message key="loginBtn"/>'/>
        </form>
        一定要过SpringMVC的视图解析流程，人家会创建一个jstlView帮你快速国际化；
        也不能写forward:（
    
        if (viewName.startsWith(FORWARD_URL_PREFIX)) {
        String forwardUrl = viewName.substring(FORWARD_URL_PREFIX.length());
        return new InternalResourceView(forwardUrl);
        }
        ）
        
        
## spring异常处理
### 类异常
在类里面生申明一个方法，该方法会处理本类的所有异常

```java
    @ExceptionHandler(Exception.class)
    public ModelAndView handelException(Exception ex){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("MyErrors");
        mv.addObject("msg",ex.getMessage());
        return mv;
    }

```
### 全局异常处理  
新建一个专门处理异常的类，
标上@@ControllerAdvice（增强controller）
 @ExceptionHandler(Exception.class)，申明异常处理方法，处理什么类型的异常
```java

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
```
