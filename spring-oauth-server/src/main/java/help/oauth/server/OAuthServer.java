package help.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import help.security.auth.SecurityConfig;
import help.security.database.DatabaseConfig;

@SpringBootApplication
@Import({
	DatabaseConfig.class, 
	SecurityConfig.class})
public class OAuthServer extends SpringBootServletInitializer 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(OAuthServer.class, args);
    }   

}
