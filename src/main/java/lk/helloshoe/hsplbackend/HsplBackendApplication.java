package lk.helloshoe.hsplbackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HsplBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HsplBackendApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
