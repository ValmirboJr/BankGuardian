package org.example.bankguardian.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.example.bankguardian.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record TransactionRequest(UUID accountId, @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime eventTime, String description,
                                 BigDecimal amount,@JsonInclude PaymentMethod paymentMethod) {
}