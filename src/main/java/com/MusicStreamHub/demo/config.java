package com.MusicStreamHub.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class config {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }

        };
    }
    public static final String CLIENT_ID ="70bc848de86d45449e7c9f2c7cfb165b";
    public static final String CLIENT_SECRET="bb967fde79934a3f86e0b995f028aadc";
    public static final String YT_API_KEY = "AIzaSyDWgtYA7UjlZmlw3LiEgyoL8vUIP3uAhF0";
    public static final String MUSIC_MATCH_API_KEY = "78d2e876b78b465128709ed4490e6057";
}