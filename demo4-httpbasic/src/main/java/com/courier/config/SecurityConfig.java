package com.courier.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {

		auth.inMemoryAuthentication().withUser("Sam").password("{noop}Sam@123")
				.roles("USER").and().withUser("Pat").password("{noop}Pat@123")
				.roles("EMPLOYEE");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and().authorizeRequests().antMatchers("/images/**")
				.permitAll().antMatchers("/**").hasAnyRole("EMPLOYEE", "USER")
				.anyRequest().authenticated().and().exceptionHandling()
				.accessDeniedPage("/accessDenied");

	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations(
				"/classpath:/static/images/**");
	}

}
