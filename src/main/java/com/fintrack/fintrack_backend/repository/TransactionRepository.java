package com.fintrack.fintrack_backend.repository;
import com.fintrack.fintrack_backend.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
List<Transaction> findByUserId(Long userId);

@Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId AND t.type = 'income'")
Double sumIncomeByUser(@Param("userId") Long userId);

@Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId AND t.type = 'expense'")
Double sumExpenseByUser(@Param("userId") Long userId);

@Query("SELECT t.category FROM Transaction t WHERE t.user.id = :userId AND t.type = 'expense' GROUP BY t.category ORDER BY SUM(t.amount) DESC")
List<Object[]> findExpenseSummaryByUser(@Param("userId") Long userId);
}

