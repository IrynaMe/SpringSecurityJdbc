package it.mario.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

//@Configuration //comment to avoid putting it in context
public class OLDAppSecurityConfig {
    @Bean
    //finds db tables & fetches data for authentication via Datasource
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    //manage authorization
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                // specify endpoints for each role
                configurer.requestMatchers("/benvenuto").permitAll()
                        .requestMatchers("/ospiti").hasRole("GUEST") //role is case sensitive ->as it is in db table
                        .requestMatchers("/impiegati").hasAnyRole("IMPIEGATO", "MANAGER")); //hasAnyRole -if more than 1 role
        http.httpBasic();
        http.csrf().disable();
        return http.build();
    }


}//
