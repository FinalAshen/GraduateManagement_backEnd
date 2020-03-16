package com.web.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;

@Configuration
@EnableWebMvc  //启用SpringMVC
@ComponentScan(basePackages = {"com.web.blog.controller", "com.web.blog.service",  "com.web.blog.dao"})  // 扫描service、repository、controller
public class DispatcherConfig {
    // 配置JSP视图解析器
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");  // 解析视图的前缀
        resolver.setSuffix(".jsp");  // 视图的后缀
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }


    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver () throws IOException {
        CommonsMultipartResolver multipartResolver =  new CommonsMultipartResolver();
        multipartResolver.setUploadTempDir(new FileSystemResource("C:/file/blog-project"));
        multipartResolver.setMaxUploadSize(1024 * 1024 * 20); // 最大20MB的文件
        multipartResolver.setMaxInMemorySize(0);
        return multipartResolver;
    }

}
