package com.comtop.exp.gateway.zuul.errorFilter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 用于当Post类型的过滤器调用发生异常时将异常信息写入Response中，复用了SendErrorFilter的逻辑
 * @author yangsai
 *
 */
public class ErrorExtFilter extends SendErrorFilter {

	private static Logger logger = LoggerFactory.getLogger(ErrorExtFilter.class);
	
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 30; // 大于ErrorFilter的值
    }

    @Override
    public boolean shouldFilter() {
        // 判断：仅处理来自post过滤器引起的异常
        RequestContext ctx = RequestContext.getCurrentContext();
        ZuulFilter failedFilter = (ZuulFilter) ctx.get("failed.filter");
        if (failedFilter != null && failedFilter.filterType().equals("post")) {
        	logger.info("Post过滤器发生异常，将由ErrorExtFilter来写入异常信息到Response。failedFilter={}", failedFilter);
            return true;
        }
        return false;
    }

}