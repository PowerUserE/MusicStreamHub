package com.MusicStreamHub.demo;
//package authorization.client_credentials;

import java.net.URI;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

public class accessTokenManager {
    public String requestAccessToken() {
        String clientID = config.CLIENT_ID;
        String clientSecret = config.CLIENT_SECRET;
        URI redirectUri = SpotifyHttpManager.makeUri("https://api.spotify.com");

//        System.out.println(clientID);

        SpotifyApi spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientID)
                .setClientSecret(clientSecret)
                // .setRedirectUri(redirectUri)
                .build();

        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
                .build();

        String accessToken = "";
        try {
            final var clientCredentials = clientCredentialsRequest.execute();
            accessToken = clientCredentials.getAccessToken();

//            System.out.println("Access Token: " + accessToken);
        } catch (Exception e) {
            System.out.println("Request failed: " + e.getMessage());
        }
        return accessToken;
    }

    public static void main(String[] args) {
        accessTokenManager atm = new accessTokenManager();
        atm.requestAccessToken();
    }
}