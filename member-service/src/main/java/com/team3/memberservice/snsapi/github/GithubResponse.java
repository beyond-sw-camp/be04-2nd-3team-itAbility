package com.team3.memberservice.snsapi.github;

import com.team3.memberservice.oauth2.dto.OAuth2Response;

import java.util.Map;

public class GithubResponse implements OAuth2Response {

    private final Map<String, Object> attribute;

    public GithubResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    @Override
    public String getProvider() {
        return "github";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        // Check if the 'email' attribute exists in the map and if it's not null
        if (attribute.containsKey("login") && attribute.get("login") != null) {
            return attribute.get("login").toString();
        } else {
            return null; // Return null if 'email' attribute does not exist or is null
        }
    }

    @Override
    public String getName() {
        // Check if the 'name' attribute exists in the map and if it's not null
        if (attribute.containsKey("name") && attribute.get("name") != null) {
            return attribute.get("name").toString();
        } else {
            return null; // Return null if 'name' attribute does not exist or is null
        }
    }

    @Override
    public String getThumbnail() {
        // Check if the 'avatar_url' attribute exists in the map and if it's not null
        if (attribute.containsKey("avatar_url") && attribute.get("avatar_url") != null) {
            return attribute.get("avatar_url").toString();
        } else {
            return null; // Return null if 'avatar_url' attribute does not exist or is null
        }
    }
}