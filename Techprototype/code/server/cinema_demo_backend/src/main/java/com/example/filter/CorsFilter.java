package com.example.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "corsFilter", urlPatterns = {"/*"})// 过滤所有的请求
public class CorsFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

//        final Logger log = LoggerFactory.getLogger(BookController.class);
//        log.info("Starting a transaction for req : {}", request.getRequestURI());
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");// 在filter中设置允许这里的请求跨域访问

        response.setHeader("Access-Control-Allow-Methods",
                "POST, GET, OPTIONS, DELETE, PUT");

        response.setHeader("Access-Control-Max-Age", "3600");

        response.setHeader("Access-Control-Allow-Headers",
                "Content-Type,Access-Token,Authorization,ybg");

        response.setHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(servletRequest, servletResponse);
    }
    @Override
    public void destroy() { }
}