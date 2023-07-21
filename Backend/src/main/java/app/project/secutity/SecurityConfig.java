package app.project.secutity;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import app.project.jwt.JwtAuthenticateEntryPoint;
import app.project.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private JwtAuthenticateEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
    private UserDetailsService userDetailsService;
	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		try {
			authProvider.setPasswordEncoder(passwordEncoder());
			authProvider.setUserDetailsService(userDetailsService());
		}catch(Exception e) {
			System.out.println("die :"+e.getMessage());
		}
		return authProvider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		try {
			auth.authenticationProvider(authenticationProvider());
			auth.userDetailsService(userDetailsService);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void configure(HttpSecurity http) {
	   try {
		     http.csrf().disable()
		        .authorizeRequests()
		        .antMatchers("/signUp").permitAll()
		        .antMatchers("/logIn").permitAll()
		        .antMatchers("/ExistMail").permitAll()
		        .antMatchers("/getAllCategorie").permitAll()
		        .antMatchers("/getAllVillages").permitAll()
				.anyRequest().authenticated()
		        .and()
			    .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 	    // Add a filter to validate the tokens with every request
				http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
	   }
	}
	

}
