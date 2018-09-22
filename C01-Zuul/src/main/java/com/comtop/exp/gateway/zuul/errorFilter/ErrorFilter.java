package com.comtop.exp.gateway.zuul.errorFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * Error类型的filter，用于统一处理filter调用过程中的异常。<br>
 * 注意：该过滤器只是将异常信息按照SendErrorFilter的要求存放到RequestContext中，最终还是由SendErrorFilter将异常信息写入到Response中<br>
 * </p>
 * 
 * <h5>存在的问题：</h5>
 * Post类型的过滤器发生异常不会把异常信息写入Response中，将会返回一个空内容<br>
 * <h5>原因：</h5>
 * 由于SendErrorFilter是Post类型的Filter，根据{@link com.netflix.zuul.http.ZuulServlet#service(ServletRequest, ServletResponse)}的处理逻辑可知，
 * post类型的Filter发生异常后只会调用Error类型的过滤器，而不会调用Post的过滤器。 <br>
 * <h5>解决方案：见{@link com.comtop.exp.gateway.zuul.errorFilter.ErrorExtFilter}</h5> 
 * </p>
 * @author yangsai
 *
 */
public class ErrorFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();
//        log.error("this is a ErrorFilter :" + throwable.getCause().getMessage(), throwable);
        log.error("Error during filtering", throwable.getCause());
        ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        ctx.set("error.exception", throwable.getCause());
        return null;
    }

}