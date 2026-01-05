package org.example.bankguardian.service;

import org.example.bankguardian.entity.Account;
import org.example.bankguardian.entity.Transaction;
import org.example.bankguardian.mapper.TransactionMapper;
import org.example.bankguardian.repository.AccountRepository;
import org.example.bankguardian.repository.TransactionRepository;
import org.example.bankguardian.request.TransactionRequest;
import org.example.bankguardian.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public TransactionResponse criarTransacao(TransactionRequest request){

        Account account = accountRepository.findById(request.accountId())
                .orElseThrow(() -> new RuntimeException("Dados não encontrados"));
        Transaction transaction = TransactionMapper.toTransaction(request);
        transaction.setAccount(account);
        transaction = transactionRepository.save(transaction);
        TransactionResponse response = TransactionMapper.toTransactionResponse(transaction);
       // transactionProducer.sendTransactionCreated(response);
        return response;
    }

    public Optional<Transaction> getTrn(UUID id) {
        return transactionRepository.findById(id);
    }
    public List<Transaction> getAllTrn() {
        return transactionRepository.findAll();
    }
    public void DeleteTrn(UUID id) {
        transactionRepository.deleteById(id);
    }

}
