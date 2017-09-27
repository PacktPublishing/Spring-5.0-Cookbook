package org.packt.secured.mvc.core;

import org.packt.secured.mvc.core.handler.CustomLogoutHandler;
import org.packt.secured.mvc.core.handler.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class AppSecurityModelD extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	private CustomLogoutHandler customLogoutHandler;
	
	@Autowired
	@Qualifier("authUserService")
	private UserDetailsService userDetailsService;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.eraseCredentials(false);
    }
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	        http
	          .authorizeRequests()
	          .antMatchers("/login**", "/after**").permitAll()
	          .anyRequest().authenticated()
	          .and()
	          .formLogin()
	          .loginPage("/login.html")
	          .defaultSuccessUrl("/deptform.html")
	          .failureUrl("/login.html?error=true")
	          .successHandler(customSuccessHandler)
	          .and()
	          .logout().logoutUrl("/logout.html")
	          .logoutSuccessHandler(customLogoutHandler);
	        
	        http.csrf().disable();
	    }
	 

	 @Override
	 public void configure(WebSecurity web) throws Exception {
	      web
	        .ignoring()
	           .antMatchers("/resources/**")
	           .antMatchers("/css/**")
	           .antMatchers("/js/**")
	           .antMatchers("/image/**");
	    }
}
