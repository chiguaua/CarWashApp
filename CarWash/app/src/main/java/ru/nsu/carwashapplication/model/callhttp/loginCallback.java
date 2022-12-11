package ru.nsu.carwashapplication.model.callhttp;

import java.util.List;

public class loginCallback {

    private String id;
    private String username;
    private String email;
    private List<String> roles;
    private String accessToken;
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
