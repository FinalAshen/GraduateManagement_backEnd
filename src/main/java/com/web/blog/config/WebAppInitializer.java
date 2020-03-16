package com.web.blog.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * 扩展AbstractAnnotationConfigDispatcherServletInitializer的任意类都会自动地配置DispatcherServlet和Spring应用上下文
 * ，在容器创建时上述两个就会加载到Servlet中
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    // 将DispatcherServlet映射到“/”
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
    // 返回ContextLoaderListener创建地应用上下文的bean
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { SpringMybatisConfig.class, EnableSchedulingConfig.class };
    }
    // 返回DispatcherServlet应用上下文的bean
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { DispatcherConfig.class };
    }
    // 添加过滤器，实现编码过滤
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[]{characterEncodingFilter};
    }
}