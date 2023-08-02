package com.MusicStreamHub.demo.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrackService {

    public List<Map<String, Object>> getArtistTracks(String query) throws IOException {
        List<Map<String, Object>> trackProfiles = new ArrayList<>();
        SearchService ss = new SearchService();
        Gson gson = new Gson();

        String JsonResponse = ss.searchArtistTracks(query);

        JsonObject responseObj = gson.fromJson(JsonResponse, JsonObject.class);
        JsonObject tracksObj = responseObj.getAsJsonObject("tracks");
        JsonArray itemsArray = tracksObj.getAsJsonArray("items");

        for (int i = 0; i < itemsArray.size(); i++) {
            JsonObject itemObj = itemsArray.get(i).getAsJsonObject();

            String name = itemObj.get("name").getAsString();

            JsonObject albumObj = itemObj.getAsJsonObject("album");
            String albumName = albumObj.get("name").getAsString();
            String releaseDate = albumObj.get("release_date").getAsString();

            JsonArray artistsArray = itemObj.getAsJsonArray("artists");
            List<String> artistNames = new ArrayList<>();
            for (int j = 0; j < artistsArray.size(); j++) {
                JsonObject artistObj = artistsArray.get(j).getAsJsonObject();
                String artistName = artistObj.get("name").getAsString();
                artistNames.add(artistName);
            }

            JsonArray imagesArray = albumObj.getAsJsonArray("images");
            List<String> imageUrls = new ArrayList<>();
            for (int j = 0; j < imagesArray.size(); j++) {
                JsonObject imageObj = imagesArray.get(j).getAsJsonObject();
                String imageUrl = imageObj.get("url").getAsString();
                if(imageUrl == null){
                    imageUrls.add("/static/assets/img/B5PP-IIIMAA-qem.png");
                }else {
                    imageUrls.add(imageUrl);
                }
            }

            Map<String, Object> trackProfile = new HashMap<>();
            trackProfile.put("name", name);
            trackProfile.put("Album", albumName);
            trackProfile.put("Release Date", releaseDate);
            trackProfile.put("Artists", artistNames);
            trackProfile.put("imageUrls", imageUrls);
            trackProfiles.add(trackProfile);

//            System.out.println("Name: " + name);
//            System.out.println("Album: " + albumName);
//            System.out.println("Release Date: " + releaseDate);
//            System.out.println("Artists: " + artistNames);
//            System.out.println("ImageUrls: " + imageUrls);
//            System.out.println("-----------------------------");

        }
        return trackProfiles;
    }
}
