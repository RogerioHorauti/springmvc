package br.com.springmvc.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()//é o que nos retorna o objeto onde podemos configurar as regras de acesso em si.
		
		//além de estar logado, o usuário precisa serADMIN	para acessar este endereço
		.antMatchers("/produtos/form").hasRole("ADMIN")
		.antMatchers("/shopping/**").permitAll()
		.antMatchers(HttpMethod.POST,"/produtos").hasRole("ADMIN")
		//todos os outros endereços que comecem com	/produtos estão liberados.
		.antMatchers("/produtos/**").permitAll()
		
		// todo request tem que ser feito por alguém autenticado
		.anyRequest().authenticated().and()
		
		//O método formLogin indica que	queremos que nosso sistema suporte autenticação baseada em um formulário comum.
		.formLogin().loginPage("/login").permitAll().and()
		
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Autowired
	private UserDetailsService users;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(users).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
}
