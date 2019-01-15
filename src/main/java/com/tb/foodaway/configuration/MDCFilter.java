package com.tb.foodaway.configuration;

import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class MDCFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            MDC.put("userName", authentication.getName());
        }

        try {
            chain.doFilter(req, resp);
        } finally {
            if (authentication != null) {
                MDC.remove("userName");
            }
        }
    }

    @Override
    public void destroy() {
    }
}
