package help.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
//@EnableOAuth2Client
public class ResourceApp2 {

    public static void main(String[] args) {
        SpringApplication.run(ResourceApp2.class, args);
    }
}