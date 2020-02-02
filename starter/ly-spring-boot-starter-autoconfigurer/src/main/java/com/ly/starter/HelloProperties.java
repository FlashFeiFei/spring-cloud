package com.ly.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

//标注该类是一个加载配置springboot配置文件的类
//配合EnableConfigurationProperties使用
@ConfigurationProperties(prefix = "ly.hello")
public class HelloProperties {
    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
