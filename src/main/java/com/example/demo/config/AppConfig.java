package com.example.demo.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.example.demo.security.AuthEntryPointJwt;
import com.example.demo.security.JwtAuthenticationFilter;


@Configuration
@ComponentScan(basePackages = {"com.example.demo.service", "com.example.demo.security"})
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppConfig implements WebMvcConfigurer {

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Value("${appName.allowedApi}")
  private String myAllowedApi;

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

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE")
        .allowedOrigins(myAllowedApi);
  }

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable().exceptionHandling()
        .authenticationEntryPoint(unauthorizedHandler).and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
        .antMatchers("/api/auth/**").permitAll().antMatchers("/api/employee/**").permitAll().anyRequest().authenticated();
    http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
