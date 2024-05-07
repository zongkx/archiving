package zong;

import net.unicon.cas.client.configuration.EnableCasClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@EnableCasClient
@SpringBootApplication
public class CasApplication {
    ApplicationContext applicationContext;
    public static void main(String[] args) {
        SpringApplication.run(CasApplication.class, args);
    }

}

