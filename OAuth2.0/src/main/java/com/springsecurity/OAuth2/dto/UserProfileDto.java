package com.springsecurity.OAuth2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserProfileDto {

	private String first_name;
	private String last_name;
	private String name;
	private String email;
	private Object picture;
	private String url;

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Object getPicture() {
		return picture;
	}

	public void setPicture(Object picture) {
		this.picture = picture;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "UserProfileDto [first_name=" + first_name + ", last_name=" + last_name + ", name=" + name + ", email="
				+ email + ", picture=" + picture + ", url=" + url + "]";
	}

}
