package org.example.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
               .csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(request -> request
                       .requestMatchers(HttpMethod.GET,"/api/hello").authenticated()
//                       .requestMatchers("/api/hello").permitAll()
//                       .requestMatchers(HttpMethod.GET,"/api/dashboard").authenticated()
//                       .requestMatchers(HttpMethod.GET,"/api/dashboard/*").authenticated()
                       .anyRequest().permitAll()
               )
               .oauth2Login(oauth2 -> oauth2
                       .defaultSuccessUrl("http://localhost:5173/"));
       return http.build();
    }
}
