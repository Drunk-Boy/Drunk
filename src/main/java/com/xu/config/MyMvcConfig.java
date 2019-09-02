package com.xu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;

//使用WebMvcConfigurerAdapter可以来扩展springMvc配置
//@EnableWebMvc可以接管MVC
//@EnableWebMvc
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    //通过重写WebMvcConfigurerAdapter()内的addViewControllers()方法来修改springMvc的配置
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
//        registry.addViewController("/index").setViewName("index");
    }
    //通过向容器中加入WebMvcConfigurerAdapter()来为springMvc增加配置
    //一定要把WebMvcConfigurerAdapter()加入IOC容器
    @Bean
    public WebMvcConfigurerAdapter WebMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter(){
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
//                super.addViewControllers(registry);
                registry.addViewController("/*.html").setViewName("login");
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/goRegister").setViewName("register/registerView");
                registry.addViewController("/index").setViewName("index");
//                registry.addViewController("/emp/add").setViewName("elder/elderAdd");
//                registry.addViewController("/*").setViewName("top");
            }
        };
        return adapter;
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }


}
