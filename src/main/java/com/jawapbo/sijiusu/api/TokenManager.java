package com.jawapbo.sijiusu.api;

import com.jawapbo.sijiusu.response.TokenResponse;

public class TokenManager {
    private static String accessToken;
    private static String refreshToken;

    public static void setTokens(TokenResponse tokens) {
        accessToken = tokens.accessToken();
        refreshToken = tokens.refreshToken();
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
