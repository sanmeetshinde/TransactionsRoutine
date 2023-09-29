package com.transaction.service.service;

import com.transaction.service.model.Account;
import com.transaction.service.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service for managing Accounts.
 */
@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    /**
     * Returns the Account for the corresponding Account ID.
     * @param accountId
     * @return Optional<Account>
     */
    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    /**
     * Saves the Account provided as argument.
     * @param account
     */
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

}
