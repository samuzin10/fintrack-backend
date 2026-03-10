package com.fintrack.fintrack_backend.controller;

import com.fintrack.fintrack_backend.model.Transaction;
import com.fintrack.fintrack_backend.service.TransactionService;
import com.fintrack.fintrack_backend.dto.CategorySummaryResponse;
import com.fintrack.fintrack_backend.dto.CreateTransactionDTO;
import com.fintrack.fintrack_backend.dto.DashboardResponse;
import com.fintrack.fintrack_backend.dto.TransactionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    @GetMapping
    public Page<TransactionResponseDTO> listTransactions(
        @RequestParam Long userId,
        Pageable pageable){

            return transactionService.listTransactions(userId, pageable);
        }
    }

