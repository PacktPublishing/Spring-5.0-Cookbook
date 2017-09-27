package org.packt.web.reactor.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.inMemoryAuthentication()
          .withUser("sjctrags").password("sjctrags").roles("USER");
    }
 	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
          .antMatchers("/react/login**", "/react/after**").permitAll()
          .anyRequest().authenticated()
          .and()
          .formLogin()
          .loginPage("/react/login.html")
          .defaultSuccessUrl("/react/menu.html")
          .failureUrl("/react/login.html?error=true")
          .and().logout().logoutUrl("/react/logout.html")
          .logoutSuccessUrl("/react/after_logout.html")
          .and().sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
          
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
