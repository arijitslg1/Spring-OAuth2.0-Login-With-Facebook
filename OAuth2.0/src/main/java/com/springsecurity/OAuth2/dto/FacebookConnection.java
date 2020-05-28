package com.springsecurity.OAuth2.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FacebookConnection {

	@Value("${security.oauth2.client.clientId}")
	private String clientId;

	@Value("${security.oauth2.client.clientSecret}")
	private String clientSecret;

	@Value("${security.oauth2.client.userAuthorizationUri}")
	private String userAuthUri;

	@Value("${security.oauth2.client.accessTokenUri}")
	private String accessTokenUri;

	@Value("${security.oauth2.resource.userInfoUri}")
	private String userInfoUri;

	@Value("${redirectUri}")
	private String reDirectUri;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getUserAuthUri() {
		return userAuthUri;
	}

	public void setUserAuthUri(String userAuthUri) {
		this.userAuthUri = userAuthUri;
	}

	public String getAccessTokenUri() {
		return accessTokenUri;
	}

	public void setAccessTokenUri(String accessTokenUri) {
		this.accessTokenUri = accessTokenUri;
	}

	public String getUserInfoUri() {
		return userInfoUri;
	}

	public void setUserInfoUri(String userInfoUri) {
		this.userInfoUri = userInfoUri;
	}

	public String getReDirectUri() {
		return reDirectUri;
	}

	public void setReDirectUri(String reDirectUri) {
		this.reDirectUri = reDirectUri;
	}

}
