package com.bloggingApp.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.bloggingApp.security.CustomUserDetailsService;
import com.bloggingApp.security.JwtAuthenticationEntryPoint;
import com.bloggingApp.security.JwtAuthenticationFilter;

;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	public static final String[] PUBLIC_URLS = { "/api/v1/auth/**", "/v3/api-docs" };
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtauthenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());
		http.authorizeHttpRequests((authorize) -> authorize.requestMatchers(PUBLIC_URLS).permitAll()

				.requestMatchers(HttpMethod.GET).permitAll().anyRequest().authenticated())
				.exceptionHandling(exception -> exception.authenticationEntryPoint(this.jwtauthenticationEntryPoint));
		http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.authenticationProvider(authenticationProvider());

		DefaultSecurityFilterChain build = http.build();
		return build;

	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(this.customUserDetailsService);
		dao.setPasswordEncoder(passwordEncoder());
		return dao;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}

	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	
	/* * @Bean public FilterRegistrationBean corsFilters() {
	 * UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); CorsConfiguration corsConfiguration = new
	 * CorsConfiguration(); corsConfiguration.setAllowCredentials(true);
	 * corsConfiguration.addAllowedOriginPattern("*");
	 * corsConfiguration.addAllowedHeader("Authorization");
	 * corsConfiguration.addAllowedHeader("Content-Type");
	 * corsConfiguration.addAllowedHeader("Accept");
	 * corsConfiguration.addAllowedMethod("POST");
	 * corsConfiguration.addAllowedMethod("GET");
	 * corsConfiguration.addAllowedMethod("DELETE");
	 * corsConfiguration.addAllowedMethod("PUT");
	 * corsConfiguration.addAllowedMethod("OPTIONS");
	 * corsConfiguration.setMaxAge(3600L);
	 * 
	 * FilterRegistrationBean bean = new FilterRegistrationBean(new
	 * CorsFilter((CorsConfigurationSource) source)); bean.setOrder(-110); return
	 } ;*/
	 
	 
}
