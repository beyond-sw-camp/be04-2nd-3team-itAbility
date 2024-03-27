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
        // Using Optional to safely handle null values and ensuring a non-null String is returned.
        return Optional.ofNullable(attribute.get("sub"))
                .map(Object::toString)
                .orElse(""); // Returning empty String instead of null.
    }

    @Override
    public String getEmail() {
        return Optional.ofNullable(attribute.get("email"))
                .map(Object::toString)
                .orElse(""); // Consistently handling Optional to return non-null String.
    }

    @Override
    public String getName() {
        return Optional.ofNullable(attribute.get("name"))
                .map(Object::toString)
                .orElse(""); // Ensuring consistency in handling potential nulls.
    }

    @Override
    public String getThumbnail() {
        return Optional.ofNullable(attribute.get("picture"))
                .map(Object::toString)
                .orElse(""); // Maintaining the approach to avoid null returns.
    }
}
