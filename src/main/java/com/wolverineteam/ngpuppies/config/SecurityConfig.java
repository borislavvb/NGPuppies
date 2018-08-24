package com.wolverineteam.ngpuppies.config;

import com.wolverineteam.ngpuppies.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityConfig(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

                                    /** DISABLE TOKEN SECURITY */
//        http
//            .authorizeRequests()
//            .anyRequest()
//            .permitAll()
//            .and()
//            .cors()
//            .and()
//            .csrf()
//            .disable();


                                    /** ENABLE TOKEN SECURITY */

        http
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/client/**").hasRole("USER")
            .anyRequest().authenticated()
            .and()
            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
            .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userService))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
}

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(this.userService)
                    .passwordEncoder(this.bCryptPasswordEncoder);
        }
}
