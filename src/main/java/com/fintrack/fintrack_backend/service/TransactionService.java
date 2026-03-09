package com.fintrack.fintrack_backend.service;

import com.fintrack.fintrack_backend.model.Transaction;
import com.fintrack.fintrack_backend.repository.TransactionRepository;
import com.fintrack.fintrack_backend.repository.UserRepository;
import com.fintrack.fintrack_backend.model.User;
import com.fintrack.fintrack_backend.dto.CategorySummaryResponse;
import com.fintrack.fintrack_backend.dto.DashboardResponse;
import org.springframework.stereotype.Service;

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

    public Transaction createTransaction(Transaction transaction, Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public void deleteTransaction(Long id){
        transactionRepository.deleteById(id);
    }

    public Transaction updateTransaction(Long id, Transaction updateTransaction){
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
        Double income = transactionRepository.sumIncomeByUser(userId);
        Double expense = transactionRepository.sumExpenseByUser(userId);
        Double balance = (income != null ? income : 0) - (expense != null ? expense : 0);
        return new DashboardResponse(balance, income != null ? income : 0, expense != null ? expense : 0);
    }

public List<CategorySummaryResponse> getExpenseSummary(Long userId) {

    List<Object[]> results = transactionRepository.findExpenseSummaryByUser(userId);

    List<CategorySummaryResponse> response = new ArrayList<>();

    for (Object[] row : results) {
        String category = (String) row[0];
        Double total = (Double) row[1];

        response.add(new CategorySummaryResponse(category, total));
    }

    return response;
}
}