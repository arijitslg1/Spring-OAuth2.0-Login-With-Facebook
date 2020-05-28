package com.springsecurity.OAuth2.controllers;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springsecurity.OAuth2.dto.UserProfileDto;
import com.springsecurity.OAuth2.service.FacebookServiceImpl;

@Controller
public class LoginController {

	@Autowired
	private FacebookServiceImpl facebookServiceImpl;
	
	
	@GetMapping("/")
	public String getControl() {
		return "index.jsp";
	}

	@GetMapping("/login")
	public String getLogin() {
		return "index.jsp";
	}

	@GetMapping("/externalUrl")
	public String openExternalUrl() throws UnsupportedEncodingException {
		String fbLoginUrl = facebookServiceImpl.getFbLoginUrl();
		return "redirect:" + fbLoginUrl;
	}

	@GetMapping("/token")
	@ResponseBody
	public ModelAndView getToken(HttpServletRequest request) throws UnsupportedEncodingException {
		ModelAndView modelAndView = new ModelAndView("UserInfo.jsp");
		String code = request.getParameter("code");
		String accessTokenUrl = facebookServiceImpl.getFBTokenURL(code);
		String accessToken = facebookServiceImpl.getAccessToken(accessTokenUrl);
		String userInfoUrl = facebookServiceImpl.getFBGraphURL(accessToken);
		UserProfileDto userProfileDto = facebookServiceImpl.getUserInfo(userInfoUrl);
		modelAndView.addObject("user", userProfileDto);

		return modelAndView;

	}

}