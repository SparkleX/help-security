package help.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.config.annotation.web.http.SpringHttpSessionConfiguration;

//@Configuration
public class ServiceLayerSessionConfig extends SpringHttpSessionConfiguration 
{
	@Bean
	public ServiceLayerSessionRepository sessionRepository()
	{
		ServiceLayerSessionRepository sessionRepository = new ServiceLayerSessionRepository();
		return sessionRepository;
	}
}
