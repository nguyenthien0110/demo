package com.example.demo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
@ComponentScan(basePackages = {"com.example.demo.service"})
public class AppConfig{

  @Bean
  public DriverManagerDataSource dataSource() throws IOException {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    Properties properties = new Properties();
    InputStream user_props = this.getClass().getResourceAsStream("/application.properties");
    properties.load(user_props);
    dataSource.setDriverClassName(properties.getProperty("spring.datasource.driver-class-name"));
    dataSource.setUrl(properties.getProperty("spring.datasource.url"));
    dataSource.setUsername(properties.getProperty("spring.datasource.username"));
    dataSource.setPassword(properties.getProperty("spring.datasource.password"));
    return dataSource;
  }

  @Bean
  public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
    Resource resource = new ClassPathResource("Mapper/SqlMapConfig.xml");
    SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
    sqlSessionFactory.setDataSource(dataSource());
    sqlSessionFactory.setConfigLocation(resource);
    return sqlSessionFactory;
  }

  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
    MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
    mapperScannerConfigurer.setBasePackage("com.example.demo.repository");
    mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
    return mapperScannerConfigurer;
  }
}
