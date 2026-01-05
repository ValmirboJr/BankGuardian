package org.example.bankguardian.service;

import org.example.bankguardian.entity.Account;
import org.example.bankguardian.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        if(accountRepository.findByEmail(account.getEmail()).isPresent()){
            return accountRepository.findByEmail(account.getEmail()).get();
        }
    return accountRepository.save(account);
    }

    public Optional<Account> findById(UUID id) {
        return accountRepository.findById(id);
    }
    public List<Account> ListAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> updateAccount(UUID id, Account AccountUpdate) {
        Optional<Account> Update = accountRepository.getAccountsById(id);
        if (Update.isPresent()) {
            Account Accounts = Update.get();
            accountRepository.save(AccountUpdate);
            return Optional.of(AccountUpdate);
        }
        return Optional.empty();
    }
    public void deleteAccount(UUID id) {
        accountRepository.deleteById(id);
    }
}