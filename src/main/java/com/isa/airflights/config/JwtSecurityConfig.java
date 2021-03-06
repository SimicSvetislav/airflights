package com.isa.airflights.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.isa.airflights.security.jwt.JwtAuthEntryPoint;
import com.isa.airflights.security.jwt.JwtAuthTokenFilter;
import com.isa.airflights.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true
)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    //
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/abstractUsers/**").permitAll()

                .antMatchers("/api/hotel/**").permitAll()
                .antMatchers("/api/room/**").permitAll()
                .antMatchers("/api/extras/**").permitAll()
                .antMatchers("/api/admin/**").permitAll()
                .antMatchers("/api/rentacar/**").permitAll()
                .antMatchers("/api/airline/**").permitAll()
                //.antMatchers("/api/vehicle/**").hasAnyRole("SYSTEMADMIN","RENTACARADMIN")
                //.antMatchers("/api/rentacar/**").hasAnyRole("SYSTEMADMIN","RENTACARADMIN")
                //.antMatchers("/api/branch/**").hasAnyRole("SYSTEMADMIN","RENTACARADMIN")

                .antMatchers("/api/rentacar/search/**").permitAll()
                .antMatchers("/api/reservation/**").permitAll()
                .antMatchers("/api/branch/getAllBranches").permitAll()
                
                .antMatchers("/api/vehicle/**").permitAll()//hasAnyRole("SYSTEMADMIN","RENTACARADMIN")              
                .antMatchers("/api/branch/**").permitAll()//hasAnyRole("SYSTEMADMIN","RENTACARADMIN")
                .antMatchers("/api/rentacar/test").permitAll()//hasAnyRole("SYSTEMADMIN","RENTACARADMIN")
                .antMatchers("/api/rentacar/{id}").permitAll()//hasAnyRole("SYSTEMADMIN","RENTACARADMIN")
                
                .antMatchers("/api/airline/airplane/**").permitAll()
                .antMatchers("\"/api/airline/**").permitAll()
                
                
                .antMatchers("/api/flight/**").permitAll()
                /*.antMatchers("/api/vehicle/**").hasRole("RENTACARADMIN") 
                .antMatchers("/api/rentacar/**").hasRole("RENTACARADMIN") 
                .antMatchers("/api/branch/**").hasRole("RENTACARADMIN") */
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}