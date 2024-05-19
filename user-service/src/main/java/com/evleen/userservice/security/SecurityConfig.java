//package com.evleen.userservice.security;
//import org.springframework.context.annotation.Bean;
//
//import org.springframework.context.annotation.Bean;
//
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.Customizer;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.web.SecurityFilterChain;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig {
////
////	@Bean
////    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
////        http
////            .authorizeHttpRequests(authorizeRequests ->
////                authorizeRequests
////                    .requestMatchers("/login").permitAll()
////                    .anyRequest().authenticated()
////            )
////            .httpBasic(Customizer.withDefaults())
////            .csrf(csrf -> csrf.disable())
////            .headers(headers -> {
////                headers
////                    .httpStrictTransportSecurity(Customizer.withDefaults())
////                    .xssProtection(Customizer.withDefaults())
////                    .contentSecurityPolicy(csp -> csp.policyDirectives("default-src 'self'"));
////            });
////        return http.build();
////    }
////}
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//	// register the WebSecurity̰Customizer bean to configure web security
//	@Bean
//	WebSecurityCustomizer webSecurityCustomizer() {
//		return (web) -> web.ignoring().requestMatchers("/js/**", "/css/**");
//	}
//
//	@Bean
//	InMemoryUserDetailsManager userDetailsService() {
//		UserDetails user = User.withDefaultPasswordEncoder().username("admin").password("password").roles("USER")
//				.build();
//
//		return new InMemoryUserDetailsManager(user);
//	}
//
//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		// all requests should be authenticated except one is requestmatchers
//		http.authorizeHttpRequests(
//				request -> request.requestMatchers("/users").permitAll().anyRequest().authenticated());
//		// if request is not authenticated, a web page is shown
//		http.httpBasic(Customizer.withDefaults());
//
//		// CSRF - POST, PUT
//		http.csrf(csrf -> csrf.disable());
//
//		return http.build();
//	}
//}