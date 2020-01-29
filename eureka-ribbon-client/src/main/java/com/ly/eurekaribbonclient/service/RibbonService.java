package com.ly.eurekaribbonclient.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RibbonService {
    @Autowired
    RestTemplate restTemplate;

    //    让hi方法具有熔断器功能,当熔断器打开时，会执行hiError方法
    @HystrixCommand(fallbackMethod = "hiError")
    public String hi(String name) {
        //eureka-client是 eureka-client项目的名字，spring.application.name中定义
        return restTemplate.getForObject("http://eureka-client/hi?name=" + name, String.class);
    }

    //熔断器
    public String hiError(String name) {
        //这里可以做释放资源、返回静态资源操作
        //这个方法的逻辑不能写得太复杂
        return "hi," + name + " , sorry,error!";
    }
}
