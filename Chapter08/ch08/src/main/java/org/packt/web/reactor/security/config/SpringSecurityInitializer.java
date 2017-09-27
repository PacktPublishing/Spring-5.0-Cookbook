package org.packt.web.reactor.security.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	
	@Override
	protected boolean isAsyncSecuritySupported() {
		return true;
	}
	
	@Override
	protected EnumSet<DispatcherType> getSecurityDispatcherTypes() {
		return EnumSet.of(DispatcherType.ASYNC, DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE);
	}
}
