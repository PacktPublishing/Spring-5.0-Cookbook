package org.packt.secured.mvc.core;

import org.packt.secured.mvc.core.handler.CustomLogoutHandler;
import org.packt.secured.mvc.core.handler.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled=true)
public class AppSecurityModelG extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	private CustomLogoutHandler customLogoutHandler;
	
	@Autowired
	@Qualifier("authUserService")
	private UserDetailsService userDetailsService;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {    
		auth.authenticationProvider(authProvider());
		auth.eraseCredentials(false);
    }

	 @Override
	 protected void configure(HttpSecurity http) throws Exception {
	        http
	          .authorizeRequests()
	          .antMatchers("/login**", "/after**").permitAll()
	          .antMatchers("/session*").permitAll()
	          .anyRequest().authenticated()
	          .and()
	          .formLogin()
	          .loginPage("/login.html")
	          .defaultSuccessUrl("/deptform.html")
	          .failureUrl("/login.html?error=true")
	          .successHandler(customSuccessHandler)
	          .and()
	          .logout().logoutUrl("/logout.html")
	          .logoutSuccessHandler(customLogoutHandler)
	          .invalidateHttpSession(true)
	          .deleteCookies("JSESSIONID")
	          .and()
	          .exceptionHandling().accessDeniedPage("/access_denied.html");
	        
	        
	        http.sessionManagement()
	          .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
	          .maximumSessions(1)
	          .maxSessionsPreventsLogin(true)
	          .expiredUrl("/session_expired.html")
	          .and()
	          .enableSessionUrlRewriting(true)
	          .invalidSessionUrl("/session_invalid.html");
      
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
	 
	 @Bean
	 public Md5PasswordEncoder md5PasswordEncoder(){
		 Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		 md5.setEncodeHashAsBase64(true);
		 md5.setIterations(32);
		 return md5;
	 }
	 
	 @Bean
	 public DaoAuthenticationProvider authProvider() {
		 DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
			daoProvider.setPasswordEncoder(md5PasswordEncoder());
			daoProvider.setUserDetailsService(userDetailsService);
			ReflectionSaltSource saltHash = new ReflectionSaltSource();
			saltHash.setUserPropertyToUse("username");
			daoProvider.setSaltSource(saltHash);
	     return daoProvider;
	 }

}
