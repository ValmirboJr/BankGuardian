package org.example.bankguardian.request;

import lombok.Builder;

@Builder
public record AccountRequest(String email, String password,String username) {
}
