package com.fintrack.fintrack_backend.service;

import com.fintrack.fintrack_backend.model.Transaction;
import com.fintrack.fintrack_backend.repository.TransactionRepository;
import com.fintrack.fintrack_backend.repository.UserRepository;
import com.fintrack.fintrack_backend.model.User;
import com.fintrack.fintrack_backend.dto.CategorySummaryResponse;
import com.fintrack.fintrack_backend.dto.DashboardResponse;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
@Service

public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository){
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Transaction createTransaction(Transaction transaction, @NonNull Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }
    
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public void deleteTransaction(@NonNull Long id){
        transactionRepository.deleteById(id);
    }

    public Transaction updateTransaction(@NonNull Long id, Transaction updateTransaction){
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));

        transaction.setDescription(updateTransaction.getDescription());
        transaction.setAmount(updateTransaction.getAmount());
        transaction.setCategory(updateTransaction.getCategory());
        transaction.setDate(updateTransaction.getDate());

        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByUser(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public DashboardResponse getDashboard(Long userId){
        List<Transaction> transactions = transactionRepository.findByUserId(userId);

        double totalIncome = 0;
        double totalExpense = 0;

        for(Transaction t : transactions){
            if ("income".equalsIgnoreCase(t.getType())){
                totalIncome += t.getAmount();
            }
            if ("expense".equalsIgnoreCase(t.getType())){
                totalExpense += t.getAmount();
            }
        }

        double balance = totalIncome - totalExpense;

        return new DashboardResponse(balance, totalIncome, totalExpense);
    }

public List<CategorySummaryResponse> getExpenseSummary(Long userId) {

    return transactionRepository.getExpenseSummaryByUser(userId);
}
}