package com.security.RBA.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	/**
	 * this class is the root now for : security.
	 * 
	 * a method which has been return object -bean
	 * 
	 * HttpSecurity-is a final class 
	 * 
	 * UserDetailsService-> it is a interface it has all information about user
	 * 
	 *     	UserDetails-> it is a interface
	 *     
	 *     {noop}-> if you dont add any password encoder then use noop
	 *     
	 *    SecurityFilterChain-> it filters the data 
	 * @throws Exception 
    */
	
    @Bean
    public SecurityFilterChain mysecurity(HttpSecurity http) throws Exception
    {
    	http.csrf()
    	  .disable()
    	  .authorizeHttpRequests()
    	  .requestMatchers(HttpMethod.POST).permitAll()
    	  .anyRequest().authenticated().and().httpBasic();
    	  
    	  
    	  
		return http.build() ;
    	
    }
    @Bean
    public UserDetailsService getuser()
    {
    	UserDetails user_1=User.builder().username("Tejal").password("{noop}tejal@123").build();
    	
    	UserDetails user_2=User.builder().username("sejal").password("{noop}sejal@123").build();
    	
    	return new InMemoryUserDetailsManager(user_1,user_2);
    }

}
