package org.packt.secured.mvc.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.packt.secured.mvc.core.handler.AppAnonAuthFilter;
import org.packt.secured.mvc.core.handler.AppAuthPoint;
import org.packt.secured.mvc.core.handler.AppAuthenticationFilter;
import org.packt.secured.mvc.core.handler.CustomFailureHandler;
import org.packt.secured.mvc.core.handler.CustomLogoutHandler;
import org.packt.secured.mvc.core.handler.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//@Configuration
//@EnableWebSecurity
public class AppSecurityModelC extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private CustomSuccessHandler customSuccessHandler;
	
	@Autowired
	private CustomFailureHandler customFailureHandler;
	
	@Autowired
	private CustomLogoutHandler customLogoutHandler;
		
	@Autowired
	private AuthenticationProvider appAdminProvider;
	
	@Autowired
	private AuthenticationProvider appHRProvider;
	
	@Autowired
	private AuthenticationManager appAuthenticationMgr;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {  }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 
	        http
	          .anonymous().authorities("ROLE_ANONYMOUS")
	          .and()
	          .authorizeRequests()
	          .antMatchers("/login**", "/after**").permitAll()
	          .antMatchers("/deptanon.html").anonymous()
	          .anyRequest().authenticated()       
	          .and()
	          .formLogin()
	          .loginPage("/login.html")
	          .defaultSuccessUrl("/deptform.html")
	          .failureHandler(customFailureHandler)
	          .successHandler(customSuccessHandler)
	          .and()
	          .addFilterBefore(appAnonAuthFilter(), UsernamePasswordAuthenticationFilter.class)
	          .addFilter(appAuthenticationFilter(authenticationManager()))
	          .logout().logoutUrl("/logout.html")
	          .logoutSuccessHandler(customLogoutHandler)
	          .and().exceptionHandling().authenticationEntryPoint(setAuthPoint());
	        
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
	  
	  
	  @Bean
	  public UsernamePasswordAuthenticationFilter appAuthenticationFilter(AuthenticationManager authMgr) {
			 AppAuthenticationFilter filter = new AppAuthenticationFilter();
			 filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login.html", "POST") );
			 filter.setAuthenticationManager(authMgr);
			 return filter;
	  }
	   
	  @Bean
	  public AnonymousAuthenticationFilter appAnonAuthFilter(){
		   List<GrantedAuthority> anonAuth = new ArrayList<>();  
		   anonAuth.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
		   AppAnonAuthFilter anonFilter = new AppAnonAuthFilter("ANONYMOUS","guest",anonAuth);
	       return  anonFilter;
	   }
	   
	   @Bean
	   public AuthenticationEntryPoint setAuthPoint(){
		  return new AppAuthPoint("/login.html");
	   }
	 
	  
	   @Bean
	   public AuthenticationTrustResolver trustResolver() {
			return new AuthenticationTrustResolver() {

				@Override
				public boolean isRememberMe(final Authentication authentication) {
					return true;
				}

				@Override
				public boolean isAnonymous(final Authentication authentication) {
					Collection<? extends GrantedAuthority> auths = authentication.getAuthorities();
					 List<String> roles = new ArrayList<String>();
				        for (GrantedAuthority a : auths) {
				            roles.add(a.getAuthority());
				        }
					if(roles.contains("ROLE_ANONYMOUS") || roles.size() == 0){
						System.out.println("anon");
						return true;
					}else{
						System.out.println("not anon");
						return false;
					}
					
				}
			};
		}
}
