package org.example.bankguardian.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.example.bankguardian.entity.Account;
import org.example.bankguardian.mapper.AccountMapper;
import org.example.bankguardian.request.AccountRequest;
import org.example.bankguardian.response.AccountResponse;
import org.example.bankguardian.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/criar")
    public ResponseEntity<AccountResponse> createAccount(@RequestBody AccountRequest request) {
        Account account = accountService.createAccount(AccountMapper.toAccount(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(AccountMapper.toAccountResponse(account));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> Allaccounts() {
        return ResponseEntity.ok(accountService.ListAccounts()
                .stream().map(AccountMapper::toAccountResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> GetAccount(@PathVariable UUID id) {
        return ResponseEntity.of(accountService.findById(id)
                .map(AccountMapper::toAccountResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable UUID id) {
        accountService.deleteAccount(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}