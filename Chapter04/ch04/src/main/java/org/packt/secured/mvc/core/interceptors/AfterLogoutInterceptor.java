package org.packt.secured.mvc.core.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AfterLogoutInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("INFO LOG ...... Entering After Logout transaction.....");
	    Long startLog = null;
		Cookie[] allCookies = request.getCookies();
		for(Cookie c : allCookies){
			if(c.getName().equalsIgnoreCase("startLog")){
				startLog = Long.parseLong(c.getValue());
				System.out.println(c.getValue());
				break;
			}
		}
		long elapsed = System.currentTimeMillis() - startLog.longValue();
		System.out.println("----------Time Elapsed: " + (elapsed/1000) + " sec ---------------");
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("INFO LOG ...... Fully Done Logout transaction.....");
		
	}

}
