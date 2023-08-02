package com.MusicStreamHub.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// @RestController
@Controller
public class controller {

    @GetMapping("/")
    public String index() {
        // return "Greetings from Spring Boot!";
        return "index";
    }

    @PostMapping("/search")
    public String searchArtist(@RequestParam("artistName") String artistName) {
        // Perform the necessary logic to search for the artist
        // You can pass the artistName to your existing methods for processing
        // Return the appropriate view or redirect to the result page
        return "index";
    }

}