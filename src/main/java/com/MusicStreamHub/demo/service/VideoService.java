package com.MusicStreamHub.demo.service;


import com.MusicStreamHub.demo.config;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class VideoService {

        public String getVideoUrl(String trackName, String artistName) {
            String videoUrl = null;
            try {
                // Replace 'YOUR_API_KEY' with the actual API key you obtained
                String apiKey = config.YT_API_KEY;

                // Set up the HTTP transport and Gson factory
                HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
                JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

                // Create a Gson object for JSON parsing
                Gson gson = new GsonBuilder().setPrettyPrinting().create();

                // Create a YouTube object to interact with the API
                YouTube youtube = new YouTube.Builder(httpTransport, jsonFactory, null)
                        .setApplicationName("youtube-search")
                        .build();

                // Define your search query
//                String queryTerm = "Your music video query";
                String queryTerm = artistName + " " + trackName;

                // Define the API request to search for videos
                YouTube.Search.List search = youtube.search().list("id,snippet");
                search.setKey(apiKey);
                search.setQ(queryTerm);
                search.setType("video");
                search.setMaxResults(1L); // Adjust the number of results you want to retrieve

                // Execute the search and process the response
                SearchListResponse searchResponse = search.execute();
                List<SearchResult> searchResults = searchResponse.getItems();

                // Process the search results
                for (SearchResult result : searchResults) {
                    String videoId = result.getId().getVideoId();
                    String title = result.getSnippet().getTitle();
                    videoUrl = "https://www.youtube.com/embed/" + videoId;

                    // Convert the SearchResult object to JSON string using Gson
                    String jsonResult = gson.toJson(result);

                    System.out.println("Title: " + title);
                    System.out.println("Video ID: " + videoId);
                    System.out.println("Video URL: " + videoUrl);
                    System.out.println("JSON Result: " + jsonResult);
                    System.out.println();
                }
            } catch (IOException | GeneralSecurityException e) {
                System.err.println("An error occurred: " + e);
            }
            return videoUrl;
        }
    }




