package com.jawapbo.sijiusu.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jawapbo.sijiusu.utils.Mapper;
import com.jawapbo.sijiusu.utils.Role;
import com.jawapbo.sijiusu.utils.StyledAlert;

import java.util.Base64;
import java.util.Map;

public class JWTHandler {
    @SuppressWarnings("unchecked")
    public static Role extractRole(String jwt) throws JsonProcessingException {
        var parts = jwt.split("\\.");
        if(parts.length < 2) {
            StyledAlert.show("Error", "Invalid JWT token");
            throw new RuntimeException("Invalid JWT token");
        }

        var payload = parts[1];

        byte[] decodedBytes = Base64.getUrlDecoder().decode(payload);
        var decodedPayload = new String(decodedBytes);

        Map<String, Object> payloadMap = Mapper.getInstance().readValue(decodedPayload, Map.class);

        return Role.valueOf((String) payloadMap.get("role"));
    }
}
