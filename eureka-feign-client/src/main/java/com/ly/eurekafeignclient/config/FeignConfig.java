package com.ly.eurekafeignclient.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import feign.Retryer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

//feignConfig配置
@Configuration
public class FeignConfig {

    @Bean
    public Retryer feignRetryer() {
        //重写FeignConfig的http重连配置
        //重试间隔为100毫秒,最大重试时间为1秒，重试五次
        return new Retryer.Default(100, SECONDS.toMicros(1), 5);
    }

    /**
     * 向Hystrix-dashboard发送流消息
     * 熔断器监控
     */
    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        registrationBean.addUrlMappings("/hystrix.stream");
        return registrationBean;
    }
}
