package org.example.bankguardian.response;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record AccountResponse(UUID accountid, String username, String email, List<TransactionResponse> transactions) {
}