package com.ly.servicezuul.provider;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 为网关添加熔断器,
 * 书上说ZuulFallbackProvider，我没有找到这个接口
 * 找到了FallbackProvider接口，可能版本不对把
 */
@Component
public class MyFallbackProvider implements FallbackProvider {

    /**
     * 为哪个服务添加熔断器
     * 这里为所配置在这个网关服务添加熔断器
     */
    @Override
    public String getRoute() {

//        return "eureka-client";         //
        return "*"; //为所有服务添加熔断器
    }

    /**
     * @param route 失败的服务
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {
            @Override
            //响应码
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            //这是啥？多部件的响应码?
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            //这是啥?
            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            //响应体
            public InputStream getBody() throws IOException {

                String errmsg = "网关:" + route + "熔断器打开了，请求异常了";
                return new ByteArrayInputStream(errmsg.getBytes("UTF-8"));

            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                return httpHeaders;
            }
        };
    }
}
