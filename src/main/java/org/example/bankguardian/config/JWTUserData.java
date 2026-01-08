package org.example.bankguardian.config;

import lombok.Builder;

import java.util.UUID;

@Builder
public record JWTUserData(UUID id, String email, String password) {
}
