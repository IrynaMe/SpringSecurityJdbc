package it.mario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class AppSecurityConfig {
    @Autowired
    Environment env; //object env allows access to data in .properties

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    //finds db tables & fetches data for authentication via Datasource
    public UserDetailsService userDetailsService(@Qualifier("primoDatasource") DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        //authentication
        userDetailsManager.setUsersByUsernameQuery("select username, password, enabled from utenti where username = ?");
        //authorization
        userDetailsManager.setAuthoritiesByUsernameQuery("select user, ruolo from ruoli where user = ?");
        return userDetailsManager;
    }

    //manage authorization

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                //specify endpoints for each role
                configurer
                        .requestMatchers(HttpMethod.GET, "/benvenuto",
                                "/orario", "/oraedata", "/dipendenti", "/estraidati").permitAll()
                        .requestMatchers(HttpMethod.POST, "/inserisci/utente",
                                "/inserisci/impiegato").permitAll()
                        .requestMatchers(HttpMethod.GET, "/ospiti").hasRole("GUEST")
                        .requestMatchers(HttpMethod.GET, "/impiegati").hasRole("IMPIEGATO")

        );
        http.httpBasic();
        http.csrf().disable();
        return http.build();
    }

    //create 2 @Bean to use 2 different db->a bean for each db
    //DataSource->to interrogate db ->substitutes the classic Connection(url, user, psw) to connect to db

    @Bean
    @Qualifier("primoDatasource")
    public DataSource primoDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    @Qualifier("secondoDatasource")
    //db impiegati
    public DataSource secondoDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.datasourceprimario.url"));
        dataSource.setUsername(env.getProperty("spring.datasourceprimario.username"));
        dataSource.setPassword(env.getProperty("spring.datasourceprimario.password"));
        return dataSource;
    }

    //create JDBCTemplate interface objects that specify datasource for query in db
    @Bean
    @Qualifier("jdbcTemplatePrimo")
    public JdbcTemplate jdbcTemplatePrimo(@Qualifier("primoDatasource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean
    @Qualifier("jdbcTemplateSecondo")
    public JdbcTemplate jdbcTemplateSecondo(@Qualifier("secondoDatasource") DataSource ds) {
        return new JdbcTemplate(ds);
    }


}//
