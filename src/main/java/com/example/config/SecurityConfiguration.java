package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import com.example.handler.UserHandler;

@EnableWebSecurity
public class SecurityConfiguration{

	@Autowired
	UserHandler userHandler;
	
	 @Autowired
	 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userHandler);
	        auth.authenticationProvider(authProvider());
	 }
	 
	 @Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}

		@Bean
		public DaoAuthenticationProvider authProvider() {
			DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
			authProvider.setUserDetailsService(userHandler);
			authProvider.setPasswordEncoder(passwordEncoder());
			return authProvider;
		}
		
		@Configuration
		public static class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {

			@Autowired
			SessionRepository sessionRepository;

			@Override
			protected void configure(HttpSecurity http) throws Exception {
				http.requestMatchers().antMatchers("/api/**", "/").and().csrf().disable();
				http.antMatcher("/admin*//**").authorizeRequests().anyRequest().access("hasRole('ROLE_ADMIN')").and()
				.formLogin().permitAll().loginPage("/admin/login.html")
				.defaultSuccessUrl("/admin/index.html", true)
				.failureUrl("/admin/login.html?error=loginFailed").and().exceptionHandling()
				.accessDeniedPage("/admin/AccessDenied.html").and().logout().invalidateHttpSession(Boolean.TRUE)
				.deleteCookies("JSESSIONID").logoutUrl("/admin/logout").logoutSuccessUrl("/admin/login.html")
				.and().csrf().disable();
				http.securityContext().securityContextRepository(sessionRepository).and().csrf().disable();
				http.addFilterAfter(
						new AuthenticationFilter(authenticationManager(), sessionRepository),
						SecurityContextPersistenceFilter.class).csrf().disable();
			}
		}
		
}
