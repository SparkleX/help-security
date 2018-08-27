package help;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Bean
	public PasswordEncoder encoder() 
	{		
		return NoOpPasswordEncoder.getInstance();
	}
	
  /*  @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
       	//http.authorizeRequests().antMatchers("/test1").hasRole("USER").and().formLogin();
       	http.authorizeRequests().antMatchers("/**").authenticated().and().formLogin();
       //	http.authorizeRequests().antMatchers("/test2").authenticated().and().httpBasic();
    }*/

    
    @Bean
    @Override
    public UserDetailsService userDetailsService() 
    {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("user").password("1234").roles("USER").build());
        inMemoryUserDetailsManager.createUser(User.withUsername("admin").password("1234").roles("ADMIN").build());
        return inMemoryUserDetailsManager;
    }
}