package com.ly.eurekafeignclient.dao;

import com.ly.eurekafeignclient.config.FeignConfig;
import com.ly.eurekafeignclient.hystrix.HiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//调用服务的名字
//FeignClient会负载均衡的
//value 是服务的实例
//configuration 是重写一些配置信息，自己重写的配置信息是服务调用重试次数
//fallback 服务调用失败后需要回调的类
@FeignClient(value = "eureka-client", configuration = FeignConfig.class, fallback = HiHystrix.class)
public interface EurekaClientFeign {
    //服务的方法
    @GetMapping(value = "/hi")
    //服务所需要的参数
    String sayHiFromClientEureka(@RequestParam(value = "name") String name);
}
