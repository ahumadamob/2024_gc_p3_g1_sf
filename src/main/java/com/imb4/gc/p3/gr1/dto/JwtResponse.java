package com.imb4.gc.p3.gr1.dto;

public class JwtResponse {
	private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
