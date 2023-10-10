package com.transaction.service.repository;

import com.transaction.service.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

/**
 * Repository Interface for Transaction.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM transaction where ACCOUNT_ID=:accountId ORDER BY EVENT_DATE")
    ArrayList<Transaction> findByAccountId(Long accountId);

}
