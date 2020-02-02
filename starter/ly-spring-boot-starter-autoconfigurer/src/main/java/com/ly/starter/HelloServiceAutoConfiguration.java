package com.ly.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration   //标注该类为配置类
@ConditionalOnWebApplication //web应用该配置才生效
@EnableConfigurationProperties(HelloProperties.class) //让配置文件生效
//这个相当于laravel的服务提供者
public class HelloServiceAutoConfiguration {

    @Autowired
    HelloProperties helloProperties;


    @Bean  //创建HelloService，放到容器中
    public HelloService helloService() {
       HelloService helloService =  new HelloService();
       helloService.setHelloProperties(helloProperties);
       return helloService;
    }
}
