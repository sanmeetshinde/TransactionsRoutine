package com.transaction.service.repository;

import com.transaction.service.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository Interface for Account.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
