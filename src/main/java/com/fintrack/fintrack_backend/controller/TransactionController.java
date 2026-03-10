package com.fintrack.fintrack_backend.controller;

import com.fintrack.fintrack_backend.model.Transaction;
import com.fintrack.fintrack_backend.service.TransactionService;
import com.fintrack.fintrack_backend.dto.CategorySummaryResponse;
import com.fintrack.fintrack_backend.dto.CreateTransactionDTO;
import com.fintrack.fintrack_backend.dto.DashboardResponse;
import com.fintrack.fintrack_backend.dto.TransactionResponseDTO;

import jakarta.validation.Valid;    

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/transactions")

public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/user/{userId}")
    public Transaction createTransaction(@RequestBody @Valid CreateTransactionDTO dto, @PathVariable Long userId) {
        return transactionService.createTransaction(dto, userId);
    }

    @GetMapping
    public List<TransactionResponseDTO> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        return transactionService.updateTransaction(id, transaction);
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getTransactionsByUser(@PathVariable Long userId) {
        return transactionService.getTransactionsByUser(userId);
    }

    @GetMapping("dashboard/{userId}")
    public DashboardResponse getDashboard(@PathVariable Long userId){
        return transactionService.getDashboard(userId);
    }

    @GetMapping("/category-summary/{userId}")
    public List<CategorySummaryResponse> getExpenseSummary(@PathVariable Long userId) {
        return transactionService.getExpenseSummary(userId);
    }
    }

