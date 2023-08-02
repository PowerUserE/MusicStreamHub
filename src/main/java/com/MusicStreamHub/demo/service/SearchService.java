package com.MusicStreamHub.demo.service;


import com.MusicStreamHub.demo.accessTokenManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class SearchService {

    public String searchArtistTracks(String query) throws IOException{

        accessTokenManager atm = new accessTokenManager();
        String token = atm.requestAccessToken();
        String Query = "artist:"+query;
        String encodedQuery = URLEncoder.encode(Query, StandardCharsets.UTF_8);
        StringBuilder response = new StringBuilder();
        StringBuilder results = new StringBuilder();

        String url = "https://api.spotify.com/v1/search?q=" + encodedQuery + "&type=track&limit=50";

        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + token);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

//            System.out.println("Json Response searchArtistTrackID: " + response);

            Gson gson = new Gson();
            JsonObject responseObj = gson.fromJson(response.toString(), JsonObject.class);
            JsonObject artistsObj = responseObj.getAsJsonObject("tracks");
            JsonArray itemsArray = artistsObj.getAsJsonArray("items");

            for (int i = 0; i < itemsArray.size(); i++) {
                JsonObject artistObj = itemsArray.get(i).getAsJsonObject();

                String id = artistObj.get("id").getAsString();
                results.append(id);
//                System.out.println("id: "+id);
            }
        } else {
            System.out.println("Request failed with response code: " + responseCode);
        }
        connection.disconnect();
        return response.toString();
    }

    public List <String> searchArtistDetails (String artistID) throws IOException{
        accessTokenManager atm = new accessTokenManager();
        String token = atm.requestAccessToken();

        String url = "https://api.spotify.com/v1/artists/" + artistID;


        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + token);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

//            System.out.println(response.toString());
        } else {
            System.out.println("Request failed. Response Code: " + responseCode);
        }
        return null;
    }




}
