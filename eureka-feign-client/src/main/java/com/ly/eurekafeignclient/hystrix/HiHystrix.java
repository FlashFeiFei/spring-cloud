package com.ly.eurekafeignclient.hystrix;

import com.ly.eurekafeignclient.dao.EurekaClientFeign;
import org.springframework.stereotype.Component;

@Component
public class HiHystrix implements EurekaClientFeign {

    //Feign调用服务失败后会执行这个方法
    @Override
    public String sayHiFromClientEureka(String name) {
        return "你好," + name + " , sorry error!";
    }
}
