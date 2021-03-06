package help;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter 
{
 
	@Bean
	public PasswordEncoder passwordEncoder() 
	{		
		//return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}

   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception 
    {
        auth.inMemoryAuthentication()
          .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER");
    }*/
    

    
    @Bean
    @Override
    public UserDetailsService userDetailsService() 
    {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("user").password(passwordEncoder().encode("1234")).roles("USER").build());
        inMemoryUserDetailsManager.createUser(User.withUsername("admin").password(passwordEncoder().encode("1234")).roles("ADMIN").build());
        return inMemoryUserDetailsManager;
    }
}