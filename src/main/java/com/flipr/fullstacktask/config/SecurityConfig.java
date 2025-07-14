package com.flipr.fullstacktask.config;
import static org.springframework.security.config.Customizer.withDefaults;


import com.flipr.fullstacktask.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/api/**", "/h2-console/**").permitAll() // ✅ Allow all /api/* requests
////                        .anyRequest().authenticated()
//                                .anyRequest().permitAll()
//                )
//
////                .headers(headers -> headers.frameOptions().disable()) // Allow H2 to load in frame
//                .httpBasic(withDefaults());
//
//        return http.build();
//    }



@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for H2 and APIs (during dev only)
            .headers(headers -> headers.frameOptions(frame -> frame.disable())) // ✅ Allow H2 iframe
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/h2-console/**", "/api/**").permitAll() // ✅ Allow both H2 and APIs
                    .anyRequest().authenticated() // Secure everything else
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // stateless session
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // JWT auth

    return http.build();
}

}
