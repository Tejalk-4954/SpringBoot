package com.company.user_service.dto;

public class AuthResponse {
	  private String accessToken;
	  private String refreshToken;
	  private String tokenType = "Bearer";
	  // getters/setters
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public AuthResponse(String accessToken, String refreshToken, String tokenType) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.tokenType = tokenType;
	}
	public AuthResponse() {
		super();
	}
	public AuthResponse(String accessToken, long expiresIn) {
	    this.accessToken = accessToken;
	    this.refreshToken = null;
	    this.tokenType = "Bearer";
	}
	  
	}

