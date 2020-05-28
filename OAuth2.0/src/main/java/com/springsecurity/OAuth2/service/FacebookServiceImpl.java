package com.springsecurity.OAuth2.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.springsecurity.OAuth2.dto.FacebookConnection;
import com.springsecurity.OAuth2.dto.TokenResponseDto;
import com.springsecurity.OAuth2.dto.UserProfileDto;

@Service
public class FacebookServiceImpl implements FacebookService {

	private static String accessToken = "";
	@Autowired
	private FacebookConnection facebookConnection;

	@Override
	public String getAccessToken(String accessTokenUrl) {
		if (StringUtils.isEmpty(accessToken)) {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<TokenResponseDto> responseEntity = null;
			try {
				responseEntity = restTemplate.exchange(accessTokenUrl, HttpMethod.GET, null, TokenResponseDto.class);
			} catch (HttpClientErrorException.BadRequest e) {
				e.printStackTrace();
			}
			if (Objects.nonNull(responseEntity.getBody())) {
				accessToken = responseEntity.getBody().getAccess_token();
			} else {
				throw new RuntimeException("Response not received,error fetching data from facebook api.");
			}

		}
		return accessToken;
	}

	@Override
	public UserProfileDto getUserInfo(String userInfoUrl) {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserProfileDto> responseEntity = null;
		UserProfileDto userInfo = null;
		try {
			responseEntity = restTemplate.exchange(userInfoUrl, HttpMethod.GET, null, UserProfileDto.class);
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			throw new RuntimeException("Invalid access token");
		}
		if (Objects.nonNull(responseEntity.getBody())) {
			userInfo = responseEntity.getBody();
		} else {
			throw new RuntimeException("Response not received,error fetching data from facebook api.");
		}
		LinkedHashMap<?, ?> userPic = (LinkedHashMap<?, ?>) userInfo.getPicture();
		LinkedHashMap<?, ?> userPicObject = (LinkedHashMap<?, ?>) userPic.get("data");
		userInfo.setUrl(userPicObject.get("url").toString());
		return userInfo;
	}

	@Override
	public String getFbLoginUrl() throws UnsupportedEncodingException {
		String reDirectUri = facebookConnection.getReDirectUri().trim();
		String userAuthUri = facebookConnection.getUserAuthUri();
		String clientId = facebookConnection.getClientId();
		String scope = "email,public_profile";
		reDirectUri = URLEncoder.encode(reDirectUri, "UTF-8");
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("client_id", clientId);
		queryParams.add("redirect_uri", reDirectUri);
		queryParams.add("scope", scope);
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(userAuthUri)
				.queryParams(queryParams);
		return uriComponentsBuilder.build().toUriString();
	}

	@Override
	public String getFBTokenURL(String code) {
		if (StringUtils.isEmpty(code)) {
			throw new RuntimeException("Code  not received in callback uri.");
		}
		String reDirectUri = facebookConnection.getReDirectUri().trim();
		String accessTokenUri = facebookConnection.getAccessTokenUri();
		String clientId = facebookConnection.getClientId();
		String clientSecret = facebookConnection.getClientSecret();
		MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
		queryParams.add("client_id", clientId);
		queryParams.add("redirect_uri", reDirectUri);
		queryParams.add("client_secret", clientSecret);
		queryParams.add("code", code);
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(accessTokenUri)
				.queryParams(queryParams);
		return uriComponentsBuilder.build().toUriString();
	}

	@Override
	public String getFBGraphURL(String accessToken) {
		if (StringUtils.isEmpty(accessToken)) {
			throw new RuntimeException("Access Token can not be null or empty.");
		}
		String userInfoUri = facebookConnection.getUserInfoUri();
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(userInfoUri)
				.queryParam("access_token", accessToken);
		return uriComponentsBuilder.build().toUriString();
	}

}
