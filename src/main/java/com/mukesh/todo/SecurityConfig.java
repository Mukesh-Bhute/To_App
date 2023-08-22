package com.mukesh.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.mukesh.todo.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Bean
	public SecurityFilterChain config(HttpSecurity http ) throws Exception{
		http.csrf().disable()
		.authorizeHttpRequests()
	
		.requestMatchers("/").hasAnyAuthority("ADMIN","USER")
		.requestMatchers("/user/reg").hasAuthority("ADMIN")
		.requestMatchers("/main").permitAll()
		.anyRequest().authenticated()
		.and()
		.httpBasic(Customizer.withDefaults())
		.formLogin(form -> form 
				.loginPage("/login")
				.permitAll())
		.authenticationProvider(daoAuthenticationProvider());

return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); 
			
		provider.setUserDetailsService(this.myUserDetailsService);
		provider.setPasswordEncoder(this.passswordEncoder());
		
		return provider;
	}
	
	@Bean
	public UserDetailsService users() {
		
		UserDetails user = User.builder()
							.username("user")
							.password(passswordEncoder().encode("user123") )
							.roles("USER")
							.build();
		
		UserDetails admin = User.builder()
							.username("admin")
							.password(passswordEncoder().encode("admin123") )
							.roles("USER","ADMIN")
							.build();
		
		return new InMemoryUserDetailsManager(user, admin);		
	}


}
