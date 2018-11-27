package com.molokotech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.molokotech.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/* Axel Berlot 2018
	 * Saltea archivos varios de recursos
	 */
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
		web.ignoring().antMatchers("/css/**");
		web.ignoring().antMatchers("/img/**");
		web.ignoring().antMatchers("/js/**");
	}

	@Bean
	public UserDetailsService mongoUserDetails() {
		return new CustomUserDetailsService();
	}

	/* Autentica usuario, Saltea encriptación de pass dada por Spring security 5.0 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		UserDetailsService userDetailsService = mongoUserDetails();
		auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/* Filtra quien ingresa y dónde, falta aplicar roles */
		 http.authorizeRequests().antMatchers("/resources/**").permitAll();
		 http.authorizeRequests().antMatchers("/qr/create-qr", "/create-qr", "/molokoAccess", "/prepaid-qr", "/create-prepaid-qr").authenticated().and().formLogin().loginPage("/login").permitAll();
		 http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutUrl("/logout");
		 http.csrf().disable();
	}

}
