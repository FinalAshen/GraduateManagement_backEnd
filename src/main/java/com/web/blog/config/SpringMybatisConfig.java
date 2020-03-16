package com.web.blog.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement //开启事务管理
@MapperScan(basePackages = "com.web.blog.dao") //扫描Mybatis的Mapper接口
@PropertySource("classpath:properties/jdbc.properties")  // 读取jdbc配置
public class SpringMybatisConfig {
    // 读取jdbc属性文件属性
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    //配置c3p0数据源
    @Bean
    public DataSource dataSource() throws Exception{
        ComboPooledDataSource dataSource = new ComboPooledDataSource();  // 实例化c3p0连接池
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setMaxPoolSize(30);  // 设置保持的最大连接数量
        dataSource.setMinPoolSize(10);  // 设置保持的最小连接数量
        dataSource.setInitialPoolSize(10);  // 设置连接池初始化的连接数量
        dataSource.setAutoCommitOnClose(false);  // 关闭连接后不自动提交
        dataSource.setCheckoutTimeout(1000);  // 设置连接超时时间
        dataSource.setAcquireRetryAttempts(3);  // 当获取连接失败时重新尝试的次数
        return dataSource;
    }

    //配置mybatis的sqlSessionFactoryBean
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);  // 注入连接池对象
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("config/mybatis-config.xml"));  // 注入mybatis配置文件
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));  // 注入mapper接口需要的映射文件
        sqlSessionFactoryBean.setTypeAliasesPackage("com.web.blog.entity");  // 使用别名（非全类名）
        return sqlSessionFactoryBean;
    }

    //配置事务管理
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
