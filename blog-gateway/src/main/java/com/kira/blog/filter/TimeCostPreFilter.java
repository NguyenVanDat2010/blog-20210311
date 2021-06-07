package com.kira.blog.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @author Kira
 */
@Component
public class TimeCostPreFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(TimeCostPreFilter.class);

    private static final String START_TIME_KEY = "start_time";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return -5;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        String forwardStr = ctx.getRequest().getHeader("X-Forwarded-For");
        // String forwardStr1 = ctx.getRequest().getHeader("X-Forwarded-For");
        // logger.info("X-Forwarded-For value is: {}", forwardStr1);
        String remoteAddr = "";
        if (!StringUtils.isBlank(forwardStr)) {
            String[] strArr = forwardStr.split(",");
            remoteAddr = strArr[0];
        }

        long start = Instant.now().toEpochMilli();
        logger.info("\r\n API caught: {}; \r\n Request method: {}; \r\n Remote address is: {}",
                ctx.getRequest().getRequestURI(),
                ctx.getRequest().getMethod(),
                remoteAddr
        );
        ctx.addZuulRequestHeader("remoteAddr", remoteAddr);
        RequestContext.getCurrentContext().set(START_TIME_KEY, start);
        return null;
    }
}
