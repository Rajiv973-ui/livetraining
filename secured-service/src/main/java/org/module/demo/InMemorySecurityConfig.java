package org.module.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile(value = "inmemory")
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class InMemorySecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Bean
	public PasswordEncoder encoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		
		auth.inMemoryAuthentication()
		       .withUser("india").password(encoder.encode("india")).roles("ADMIN")
		        .and().withUser("chennai").password(encoder.encode("chennai")).roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		  http.authorizeRequests().antMatchers("/orders/**")
		               .authenticated().and().httpBasic();
	
	}

	
	
	
}