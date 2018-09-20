package help;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class ServerApp 
{
	@RequestMapping("/test")
	public String create()
	{
		return "test";
	}
	public static void main(String[] args) 
	{
		System.setProperty("javax.net.ssl.keyStore", "all.p12");
		System.setProperty("javax.net.ssl.keyStorePassword", "12345678");
		System.setProperty("javax.net.ssl.MyKeyPass", "12345678");

		SpringApplication.run(ServerApp.class, args);
	}
}