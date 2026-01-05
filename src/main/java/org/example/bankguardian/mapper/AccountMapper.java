package org.example.bankguardian.mapper;

import lombok.experimental.UtilityClass;
import org.example.bankguardian.entity.Account;
import org.example.bankguardian.request.AccountRequest;
import org.example.bankguardian.response.AccountResponse;
import org.example.bankguardian.response.TransactionResponse;

import java.util.List;

@UtilityClass
public class AccountMapper {

    public static Account toAccount(AccountRequest request) {

        return Account
                .builder()
                .email(request.email())
                .password(request.password())
                .username(request.username())
                .build();
    }

    public static AccountResponse toAccountResponse(Account response) {

        List<TransactionResponse> transactions = response.getTransactions()
                .stream()
                .map(TransactionMapper::toTransactionResponse)
                .toList();

        return AccountResponse
                .builder()
                .accountid(response.getAccountid())
                .username(response.getUsername())
                .email(response.getEmail())
                .transactions(transactions)
                .build();
    }
}
