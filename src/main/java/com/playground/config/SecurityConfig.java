package com.playground.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
		http
		    .authorizeHttpRequests(authz -> authz
				.requestMatchers("/css/**").permitAll()
				.requestMatchers("/webjars/**").permitAll()
				.requestMatchers("/js/**").permitAll()
				.requestMatchers("/user/signup").permitAll()
				.requestMatchers("/user/list").hasAuthority("ROLE_ADMIN")
				.requestMatchers("/user/detail/**").hasAuthority("ROLE_ADMIN")
				.anyRequest().authenticated())
		    .formLogin(login -> login
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error")
                .usernameParameter("userId") 
                .passwordParameter("password")
		        .permitAll())
		    .logout(logout -> logout
		    	.logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout"));
		return http.build();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	/*インメモリ認証
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User
				.withUsername("user")
				.password(passwordEncoder().encode("password"))
				.roles("GENERAL")
				.build();
	    UserDetails admin = User
				.withUsername("admin")
				.password(passwordEncoder().encode("password"))
				.roles("GENERAL", "ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user, admin);
    }
    */
}