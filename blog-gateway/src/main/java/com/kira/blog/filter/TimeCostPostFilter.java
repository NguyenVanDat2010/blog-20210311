package com.kira.blog.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author Kira
 */
@Component
public class TimeCostPostFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(TimeCostPostFilter.class);

    private static final String START_TIME_KEY = "start_time";

    @Override
    public String filterType() {
        return "post";
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
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();

        long startTime = (long) ctx.get(START_TIME_KEY);

        logger.info("\r\n API end: {}; \r\nResponse status: {}; \r\nTime escape: {} milli",
                ctx.getRequest().getRequestURI(),
                ctx.getResponse().getStatus(),
                Instant.now().toEpochMilli() - startTime
        );
        return null;
    }
}
