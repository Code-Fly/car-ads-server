package com.cloud.carads.commons.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Enabling CORS support  - Access-Control-Allow-Origin
 *
 * @author zeroows@gmail.com
 *
 * <code>
 * <!-- Add this to your web.xml to enable "CORS" -->
 * <filter>
 * <filter-name>cors</filter-name>
 * <filter-class>com.elm.mb.rest.filters.CORSFilter</filter-class>
 * </filter>
 * <p>
 * <filter-mapping>
 * <filter-name>cors</filter-name>
 * <url-pattern>/*</url-pattern>
 * </filter-mapping>
 * </code>
 */

/**
 * Created by Administrator on 2017/6/15.
 */
public class CORSFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");

        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
            // CORS "pre-flight" request
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
//			response.addHeader("Access-Control-Allow-Headers", "Authorization");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Max-Age", "1");
        }

        filterChain.doFilter(request, response);
    }

}
