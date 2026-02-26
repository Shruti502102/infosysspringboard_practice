package com.courier.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		String encoded = encoder.encode("Password");
		System.out.println("encoded::: " + encoded);

		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder)
				.usersByUsernameQuery("select username,password,enabled from users where username=?")
				.authoritiesByUsernameQuery("select username, role from user_roles where username=?");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/images/**").permitAll().antMatchers("/login*").permitAll()
				.antMatchers("/**").hasAnyRole("EMPLOYEE", "USER")

				.anyRequest().authenticated().and().formLogin().loginPage("/login").usernameParameter("username")
				.passwordParameter("password").loginProcessingUrl("/doLogin").defaultSuccessUrl("/index", true)
				.failureUrl("/accessDenied").permitAll().and().exceptionHandling().accessDeniedPage("/accessDenied")
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();

	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/classpath:/static/images/**");
	}

}
