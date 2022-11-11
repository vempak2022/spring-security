package com.mphasis.spring.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration

public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Value("${app.sec.username}")
	String userName;
	@Value("${app.sec.pwd}")
	String password;
	
	@Value("${app.sec.role}")
	String userRole;
	
	
	UserDetailsService usrDetailsService = new CustomeUserDetailService();
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//auth.inMemoryAuthentication().withUser("vempa").password("password").roles("ADMIN");
	
		System.out.println(userName +"," +password+ ","+ userRole);
		/*
		 * auth.inMemoryAuthentication().withUser(userName).password(password).roles(
		 * userRole);
		 * auth.inMemoryAuthentication().withUser("vempaRao").password("dummy").roles(
		 * "USER");
		 */
		
		auth.userDetailsService(usrDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.csrf().disable();
		
		http.authorizeHttpRequests()
		.antMatchers("/sec/api/**")
		.hasAnyRole("ADMIN")
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}
	
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

}
