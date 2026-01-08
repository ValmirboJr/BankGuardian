package org.example.bankguardian.service;

import org.example.bankguardian.entity.Account;
import org.example.bankguardian.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Account createAccount(Account account) {
        if(accountRepository.findByEmail(account.getEmail()).isPresent()){
            return accountRepository.findByEmail(account.getEmail()).get();
        }
        String password = account.getPassword();
        account.setPassword(passwordEncoder.encode(password));
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
            Account accounts = Update.get();
            accounts.setEmail(AccountUpdate.getEmail());
            accounts.setPassword(passwordEncoder.encode(AccountUpdate.getPassword()));
            accountRepository.save(AccountUpdate);
            return Optional.of(AccountUpdate);
        }
        return Optional.empty();
    }
    public void deleteAccount(UUID id) {
        accountRepository.deleteById(id);
    }
}