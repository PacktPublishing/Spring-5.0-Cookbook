package org.packt.secured.mvc.webxml;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class AppSessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("app session created");
		event.getSession().setMaxInactiveInterval(10*60);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("app session destroyed");
	}

}
