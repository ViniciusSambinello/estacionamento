package com.example.demo.auth.controller.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}
