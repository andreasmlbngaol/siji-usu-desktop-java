package com.jawapbo.sijiusu.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jawapbo.sijiusu.response.TokenResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@SuppressWarnings("unused")
public class ApiClient {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String BASE_URL = "https://api.sanalab.live/api";

    public static HttpResponse<String> get(String endpoint) {
        try {
            var request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Authorization", "Bearer " + TokenManager.getAccessToken())
                .GET()
                .build();

            return sendWithRetry(request);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send GET request", e);
        }
    }

    public static HttpResponse<String> post(String endpoint, String body) {
        try {
            var request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + TokenManager.getAccessToken())
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

            return sendWithRetry(request);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send POST request", e);
        }
    }

    private static HttpResponse<String> sendWithRetry(HttpRequest request) throws Exception {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 401) {
            if(refreshToken()) {
                var newRequest = HttpRequest.newBuilder()
                    .uri(request.uri())
                    .header("Authorization", "Bearer " + TokenManager.getAccessToken())
                    .GET()
                    .build();

                return client.send(newRequest, HttpResponse.BodyHandlers.ofString());
            }
        }

        return response;
    }

    private static boolean refreshToken() throws IOException, InterruptedException {
        String json = """
            {
                "refresh_token": "%s"
            }
            """.formatted(TokenManager.getRefreshToken());

        var request = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/auth/refresh"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            var tokens = mapper.readValue(response.body(), TokenResponse.class);

            TokenManager.setTokens(tokens);
            return true;
        } else {
            return false;
        }
    }
}
