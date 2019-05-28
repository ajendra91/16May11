package com.example.demo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
public class AppSecurity extends WebSecurityConfigurerAdapter{
	
	@Resource
	public UserDetailsService uds;
	
	@Autowired
	private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Resource
    private AuthenticationEntryPoint authenticationEntryPoint;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
		auth.userDetailsService(uds).passwordEncoder(noop());
	}
	
	 @Bean(BeanIds.AUTHENTICATION_MANAGER)
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/","/signin").permitAll();
		
		//http.exceptionHandling().authenticationEntryPoint(new RESTAuthenticationEntryPoint());
		
		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		//ise lagane par automatic mylogin page nahi aayega
		
		
		http.authorizeRequests().anyRequest()
		.authenticated().and().formLogin().loginPage("/mylogin")
		.loginProcessingUrl("/log").usernameParameter("name")
		.passwordParameter("pass").defaultSuccessUrl("/").permitAll();
		
		http.logout().logoutUrl("/mylogout").logoutSuccessUrl("/").permitAll();
		
		http.formLogin().successHandler(authenticationSuccessHandler);
		
		http.addFilter(new TokenBasedAuthenticationFilter(authenticationManager()))
        .addFilter(new TokenBasedAuthorizationFilter(authenticationManager()));
	}
	
	@Bean
	public NoOpPasswordEncoder noop() {
		return (NoOpPasswordEncoder)NoOpPasswordEncoder.getInstance();
	}
	
	

}
