package com.faces.experiment;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import security.Sha1PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) {
		try {
			http.csrf().disable();
			http
				.userDetailsService(userDetailsService())
				.authorizeRequests()
				.antMatchers("/index.jsf").permitAll()
				.antMatchers("/javax.faces.resource/**").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login.jsf")
				.permitAll()
				.failureUrl("/login.jsf?error=true")
				.defaultSuccessUrl("/dashboard.jsf")
				.and()
				.logout()
				.logoutSuccessUrl("/login.jsf");
		}
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

		/** 
		 * CREATE TABLE IF NOT EXISTS `users` (
		 * 	`id` int(11) NOT NULL AUTO_INCREMENT,
		 * 	`username` varchar(256) NOT NULL,
		 * 	`password` varchar(256) NOT NULL,
		 * 	`role` varchar(256) NOT NULL,
		 * 	`enabled` smallint(6) NOT NULL DEFAULT '1',
		 * 	PRIMARY KEY (`id`)
		 * ) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;
		 */
		
	  auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new Sha1PasswordEncoder())
		.usersByUsernameQuery(
			"select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
			"select username, role from users where username=?");
	}

}

