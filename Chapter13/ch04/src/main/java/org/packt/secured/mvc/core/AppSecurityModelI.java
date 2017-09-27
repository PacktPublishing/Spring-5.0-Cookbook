package org.packt.secured.mvc.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

//@Configuration
//@EnableWebSecurity
public class AppSecurityModelI extends WebSecurityConfigurerAdapter{
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
          .withUser("sjctrags").password("sjctrags").roles("USER");
    }
 
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
          .antMatchers("/login*").permitAll()
          .antMatchers("/after_logout*").permitAll()
          .anyRequest().authenticated()
          .and()
          .formLogin()
          .loginPage("/login.html")
          .defaultSuccessUrl("/deptform.html")
          .failureUrl("/login.html?error=true")
          .and().logout().logoutUrl("/logout.html")
          .logoutSuccessUrl("/after_logout.html");
          
          
         
         http.csrf().disable();
       
         http.headers().defaultsDisabled().cacheControl()
         .and().httpStrictTransportSecurity()
         .and().contentTypeOptions().disable()
         .frameOptions().deny()
         .xssProtection().block(true)
         .and().addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","default-src 'self'"));
        
        
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
