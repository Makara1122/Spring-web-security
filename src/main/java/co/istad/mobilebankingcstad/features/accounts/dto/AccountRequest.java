package co.istad.mobilebankingcstad.features.accounts.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountRequest(
        String accountNumber,
        String accountType,

        String accountName,
        BigDecimal accountBalance,
        String userId
) {
}
