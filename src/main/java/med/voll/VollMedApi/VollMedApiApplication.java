package med.voll.VollMedApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VollMedApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VollMedApiApplication.class, args);
	}

}
