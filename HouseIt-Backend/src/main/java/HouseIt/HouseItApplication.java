package HouseIt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"HouseIt", "HouseIt.service"})
public class HouseItApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseItApplication.class, args);
	}

}
