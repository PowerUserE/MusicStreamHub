package com.MusicStreamHub.demo.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ApiRequestService {

    public void makeApiRequest() throws IOException {

        URL url = new URL("https://api.spotify.com");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();

        if (responseCode != 200){
            throw new RuntimeException("Response code: " + responseCode);
        }else{
            System.out.println("Success!");
        }
        connection.disconnect();
    }
}
