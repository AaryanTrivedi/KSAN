package com.kisan.dto;


public record AuthorizationResponse (
	String message,
	String jwt
){}
