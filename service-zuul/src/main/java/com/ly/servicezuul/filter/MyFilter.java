package com.ly.servicezuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 这个过滤器的作用是在pre阶段判断，请求头是否带token，没有直接拦截
 */
@Component
public class MyFilter extends ZuulFilter {
    /**
     * 过滤器类型,这个类型的过滤器决定在哪一步执行
     *
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 同种类型过滤器，执行优先级，越小的越先执行
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    /**
     * 表示该过滤器是否执行过滤逻辑,
     * 返回 true 则执行run方法
     * 如果返回false则不执行run方法,直接通过
     */
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //从请求的网络中获取token
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Object accessToken = request.getParameter("token");
        if (accessToken == null) {
            System.out.println("token is emplty,空 token");
            //过滤不通过
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().setContentType("text/html;charset=UTF-8");
                ctx.getResponse().getWriter().write("空 token 中文怎么西安市");
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        System.out.println("OK, 没有异常");
        return null;
    }
}
