package com.MusicStreamHub.demo;

import com.MusicStreamHub.demo.service.ApiRequestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@RestController
public class MusicStreamHubApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(MusicStreamHubApplication.class, args);
		System.out.println("Success!");

		ApiRequestService apiRequestService = new ApiRequestService();
		apiRequestService.makeApiRequest();
	}

}






