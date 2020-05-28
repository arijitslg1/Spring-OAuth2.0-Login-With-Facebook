package com.springsecurity.OAuth2.service;

import java.io.UnsupportedEncodingException;

import com.springsecurity.OAuth2.dto.UserProfileDto;

public interface FacebookService {
	public String getAccessToken(String accessTokenUrl);

	public UserProfileDto getUserInfo(String userInfoUrl);

	public String getFbLoginUrl() throws UnsupportedEncodingException;

	public String getFBTokenURL(String code);

	public String getFBGraphURL(String accessToken);
}
