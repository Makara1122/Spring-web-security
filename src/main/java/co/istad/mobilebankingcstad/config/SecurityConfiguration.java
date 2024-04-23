package co.istad.mobilebankingcstad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    // configure securityfilter chain here

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity

                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests   // allow resource regarding ui
                                .requestMatchers("/","/swagger-ui/**", "/v2/api-docs/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll()
                                .requestMatchers("/api/v1/files/**","images/**").permitAll()
                                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/v1/files/**","images/**").permitAll()
                                .requestMatchers("/api/v1/users/**").permitAll()
                                .requestMatchers("/api/v1/accounts/**").permitAll()
                .requestMatchers("/api/v1/auth/**").permitAll()
                .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .build();

    }
}
