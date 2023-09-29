package com.transaction.service.repository;

import com.transaction.service.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository Interface for Transaction.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
