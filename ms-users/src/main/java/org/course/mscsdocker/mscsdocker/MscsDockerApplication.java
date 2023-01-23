package org.course.mscsdocker.mscsdocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MscsDockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscsDockerApplication.class, args);
	}

}
