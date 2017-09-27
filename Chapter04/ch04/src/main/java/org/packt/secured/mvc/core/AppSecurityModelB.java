package org.packt.secured.mvc.core;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class AppSecurityModelB extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AuthenticationProvider appAdminProvider;
	
	@Autowired
	private AuthenticationProvider appHRProvider;
	
	@Autowired
	private AuthenticationManager appAuthenticationMgr;
		
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       
    }

	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	        http
	          .authorizeRequests()
	          .antMatchers("/login_form**","/after**").permitAll()
	          .anyRequest().fullyAuthenticated()
	          .and()
	          .formLogin()
	          .loginPage("/login_form.html")
	          .loginProcessingUrl("/login_process")
	          .defaultSuccessUrl("/deptform.html")
	          .failureUrl("/login_form.html?error=true")
	          .and() 
	          .logout().logoutUrl("/logout.html")
	          .logoutSuccessUrl("/after_logout_url.html");
	          
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
	 
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		 return new ProviderManager(Arrays.asList(appAdminProvider, appHRProvider ), appAuthenticationMgr);
	}
}
