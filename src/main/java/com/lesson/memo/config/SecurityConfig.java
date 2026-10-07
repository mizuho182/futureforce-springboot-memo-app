package com.lesson.memo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.lesson.memo.security.AdminDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final AdminDetailService adminDetailService;
	public SecurityConfig(AdminDetailService adminDetailService) {
		this.adminDetailService=adminDetailService;
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		http.userDetailsService(adminDetailService);
		http.authorizeHttpRequests(au -> au
				.requestMatchers("/admin/signup","/admin/signin").permitAll()
				.anyRequest().authenticated()
				);
		http.formLogin(form -> form
				.loginPage("/admin/signin")
				.loginProcessingUrl("/admin/signin")
				.defaultSuccessUrl("/",true)
				);
		return http.build();
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
