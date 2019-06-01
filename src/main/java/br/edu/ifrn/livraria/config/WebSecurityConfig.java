package br.edu.ifrn.livraria.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true,prePostEnabled = true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/fragmentos/**").permitAll()
				.antMatchers("/autor/**").hasAnyAuthority("ADMINISTRADOR")
				.antMatchers("/categoria/**").hasAnyAuthority("ADMINISTRADOR")
				.antMatchers("/editora/**").hasAnyAuthority("ADMINISTRADOR")
				.antMatchers("/livro/**").hasAnyAuthority("ADMINISTRADOR")
				.antMatchers("/usuario/**").hasAnyAuthority("ADMINISTRADOR")
				.antMatchers("/cidade/**").hasAnyAuthority("ADMINISTRADOR")
				.antMatchers("/pedido/**").hasAnyAuthority("ADMINISTRADOR", "CLIENTE")
				.antMatchers("http://**").hasAnyAuthority("ADMINISTRADOR", "CLIENTE")
				.antMatchers("https://**").hasAnyAuthority("ADMINISTRADOR", "CLIENTE")
				.antMatchers("/usuario/**").hasAnyAuthority("ADMINISTRADOR")
				.antMatchers("layout").permitAll()
				.antMatchers("/image/**").permitAll()
				.antMatchers("/static/**").permitAll()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/vendor/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/distribution/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers(HttpMethod.GET, "/webjars/**").permitAll()
				.antMatchers(HttpMethod.POST, "/home").hasAnyAuthority("CLIENTE", "ADMINISTRADOR")
				.antMatchers(HttpMethod.GET, "/").permitAll()
				.antMatchers(HttpMethod.GET, "/cadastrar").permitAll()
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/entrar").permitAll()
				.successForwardUrl("/home").and().logout().permitAll()
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		http.csrf().disable();
        http.headers().frameOptions().disable();
	
	}
	
	 @Autowired
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	  }
	 
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/fragments/**", "/css/**",  "/distribuition/**",  "/img/**",  "/js/**",  "/scss/**", "/h2/**");
		web.ignoring().antMatchers("/layout", "http::/**", "https::/**", "/http::/**", "/https::/**");
}
}
