package com.derezhenko.net.filter;

//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter(filterName = "weatherFilter", urlPatterns = "/weather")
//public class WeatherFilter implements Filter {
//
//    private ServletContext context;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        this.context = filterConfig.getServletContext();
//        this.context.log("LoggingFilter initialized");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//
//        HttpSession httpSession = httpServletRequest.getSession();
//
//        if (httpSession != null && servletRequest.getParameter("start") != null &&
//                servletRequest.getParameter("end") != null &&
//                servletRequest.getParameter("city") != null &&
//                servletRequest.getParameter("login") != null
//        ) {
//            String city  = servletRequest.getParameter("city");
//            String user = servletRequest.getParameter("login");
//            String start = servletRequest.getParameter("start").toString();
//            String end = servletRequest.getParameter("end").toString();
//            this.context.log("Start time: " + start);
//            this.context.log("End time: " + end);
//            this.context.log(user + ": "+ city);
//            ((HttpServletResponse) servletResponse).sendRedirect("weather.jsp");
//        } else {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
