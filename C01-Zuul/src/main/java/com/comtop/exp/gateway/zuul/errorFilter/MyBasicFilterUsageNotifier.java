package com.comtop.exp.gateway.zuul.errorFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ExecutionStatus;
import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.FilterProcessor.BasicFilterUsageNotifier;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class MyBasicFilterUsageNotifier extends BasicFilterUsageNotifier {
	private static Logger logger = LoggerFactory.getLogger(MyBasicFilterUsageNotifier.class);

	public MyBasicFilterUsageNotifier() {
		super();
		FilterProcessor.getInstance().setFilterUsageNotifier(this);
	}

	@Override
	public void notify(ZuulFilter filter, ExecutionStatus status) {
		super.notify(filter, status);
		switch (status) {
		case FAILED:
			doFailed(filter);
			break;
		default:
			break;
		}
	}

	private void doFailed(ZuulFilter failFilter) {
		// 将失败的filter信息放入RequestContext，用于Error
		logger.info("存放调用失败的过滤器到RequestContext中。failFilter={}", failFilter);
		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.set("failed.filter", failFilter);
	}

}
