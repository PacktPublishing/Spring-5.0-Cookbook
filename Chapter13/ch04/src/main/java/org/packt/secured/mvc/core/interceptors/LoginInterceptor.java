package org.packt.secured.mvc.core.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("INFO LOG ...... Fully Done login transaction.....");
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("INFO LOG ...... Beginning login transaction.....");
		Long startLog = System.currentTimeMillis();
		Cookie startTime = new Cookie("startLog",startLog.toString());
		response.addCookie(startTime);
		System.out.println("INFO LOG ...... Done Computing Start Time.....");
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("INFO LOG ...... User Successfuly logged in.....");	
	
	}
}
