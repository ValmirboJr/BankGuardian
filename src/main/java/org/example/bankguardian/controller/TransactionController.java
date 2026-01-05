package org.example.bankguardian.controller;

import lombok.RequiredArgsConstructor;
import org.example.bankguardian.mapper.TransactionMapper;
import org.example.bankguardian.request.TransactionRequest;
import org.example.bankguardian.response.TransactionResponse;
import org.example.bankguardian.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trn")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/criar")
    public ResponseEntity<TransactionResponse> register(@RequestBody TransactionRequest request){
        return ResponseEntity.ok(transactionService.criarTransacao(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<TransactionResponse> getTransactions(@RequestParam UUID id){
        return ResponseEntity.of(transactionService.getTrn(id)
                .map(TransactionMapper::toTransactionResponse));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getTransactions(){
        return ResponseEntity.ok(transactionService.getAllTrn()
                .stream().map(TransactionMapper::toTransactionResponse).toList());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TransactionResponse> deleteTransactions(@RequestParam UUID id){
        transactionService.DeleteTrn(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
