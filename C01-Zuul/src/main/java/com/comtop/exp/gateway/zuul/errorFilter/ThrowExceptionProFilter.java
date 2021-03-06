package com.comtop.exp.gateway.zuul.errorFilter;

import org.apache.log4j.Logger;
import com.netflix.zuul.ZuulFilter;

public class ThrowExceptionProFilter extends ZuulFilter {

    private static Logger log = Logger.getLogger(ThrowExceptionProFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        log.info("This is a pre filter, it will throw a RuntimeException");
        doSomething();
        return null;
    }

    private void doSomething() {
        throw new RuntimeException("Exist some errors on pre filter...");
    }

}