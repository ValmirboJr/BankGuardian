package org.example.bankguardian.mapper;

import lombok.experimental.UtilityClass;
import org.example.bankguardian.entity.Account;
import org.example.bankguardian.entity.Transaction;
import org.example.bankguardian.request.TransactionRequest;
import org.example.bankguardian.response.TransactionResponse;

@UtilityClass
public class TransactionMapper {

    public static Transaction toTransaction(TransactionRequest transactionRequest) {

        Account account = new Account();
        account.setAccountid(account.getAccountid());

        return Transaction
                .builder()
                .eventTime(transactionRequest.eventTime())
                .description(transactionRequest.description())
                .amount(transactionRequest.amount())
                .paymentMethod(transactionRequest.paymentMethod())
                .build();
    }

    public static TransactionResponse toTransactionResponse(Transaction transaction) {

        return TransactionResponse
                .builder()
                .transactionId(transaction.getTransactionId())
                .accountid(transaction.getAccount().getAccountid())
                .eventTime(transaction.getEventTime())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .paymentMethod(transaction.getPaymentMethod())
                .build();
    }
}
