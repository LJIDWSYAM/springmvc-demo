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
