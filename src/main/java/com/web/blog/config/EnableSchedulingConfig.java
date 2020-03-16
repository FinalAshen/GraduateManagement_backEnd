package com.web.blog.config;


import com.web.blog.service.imple.CrontabServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class EnableSchedulingConfig {

    @Bean
    public CrontabServiceImpl crontab() {
        return new CrontabServiceImpl();
    }


}
