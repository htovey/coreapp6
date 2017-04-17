/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package het.springapp.security;

import het.springapp.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;

/**
 *
 * @author heather
 */
@Configuration
@EnableWebSecurity
public class SecurityAdapter extends WebSecurityConfigurerAdapter{

//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return super.userDetailsServiceBean();
//    }

    protected void configure(HttpSecurity http) throws Exception {

      http.addFilterAfter(new CsrfTokenGeneratorFilter(), CsrfFilter.class)
            .authorizeRequests()
            .antMatchers("/resources/js/**", "/resources/css/**", "/font/**", "/fonts/**").permitAll()
            .antMatchers("/logout").authenticated()
            .and()
            .formLogin().loginProcessingUrl("/coreapp6/login")
            .and()
             .formLogin().failureUrl("/coreapp6/login?error")
            .loginPage("/").permitAll().isCustomLoginPage();
              
    }
    
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

