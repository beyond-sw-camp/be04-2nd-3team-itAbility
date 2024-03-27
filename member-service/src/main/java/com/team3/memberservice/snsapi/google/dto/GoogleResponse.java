package com.team3.memberservice.snsapi.google.dto;

import com.team3.memberservice.oauth2.dto.OAuth2Response;
import java.util.Map;
import java.util.Optional;

public class GoogleResponse implements OAuth2Response {

    private final Map<String, Object> attribute;

    public GoogleResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getProviderId() {
        // Using Optional to safely handle null values.
        return Optional.ofNullable(attribute.get("sub"))
                .map(Object::toString)
                .orElse(null); // or use orElse("") for an empty string default.
    }

    @Override
    public String getEmail() {
        // Using Optional to safely handle null values.
        return Optional.ofNullable(attribute.get("email"))
                .map(Object::toString)
                .orElse(null); // or use orElse("") for an empty string default.
    }

    @Override
    public String getName() {
        // Using Optional to safely handle null values.
        return Optional.ofNullable(attribute.get("name"))
                .map(Object::toString)
                .orElse(null); // or use orElse("") for an empty string default.
    }

    @Override
    public String getThumbnail() {
        // Using Optional to safely handle null values.
        return Optional.ofNullable(attribute.get("picture"))
                .map(Object::toString)
                .orElse(null); // or use orElse("") for an empty string default.
    }
}
