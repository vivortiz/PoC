package unlpbdVO.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;*/

@SpringBootApplication
/*@ComponentScan("unlpbdVO.poc")
@EnableJpaRepositories("unlpbdVO.poc") */
public class PocApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocApplication.class, args);
	}

}
