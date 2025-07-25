package com.jawapbo.sijiusu.auth;

import com.jawapbo.sijiusu.response.TokenResponse;

public class TokenManager {
    private static String accessToken;
    private static String refreshToken;

    public static void setTokens(TokenResponse tokens) {
        accessToken = tokens.accessToken();
        refreshToken = tokens.refreshToken();
    }

    public static void clearTokens() {
        // Set tokens ke null atau empty string
        // Sesuaikan dengan implementasi TokenManager Anda

        // Contoh implementasi:
        // accessToken = null;
        // refreshToken = null;

        // Atau jika menggunakan static fields:
        // TokenManager.accessToken = null;
        // TokenManager.refreshToken = null;

        System.out.println("Tokens cleared successfully");
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        TokenManager.accessToken = accessToken;
    }

    public static String getRefreshToken() {
        return refreshToken;
    }

    public static void setRefreshToken(String refreshToken) {
        TokenManager.refreshToken = refreshToken;
    }
}
