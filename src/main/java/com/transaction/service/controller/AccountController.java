package com.transaction.service.controller;

import com.transaction.service.dto.AccountDto;
import com.transaction.service.model.Account;
import com.transaction.service.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import static com.transaction.service.constants.Constants.ACCOUNTS_URI;
import static com.transaction.service.constants.Constants.ACCOUNTS_URI_PATH_VARIABLE;

/**
 * Rest Controller for Accounts.
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * GET API which returns the Account for the account_id provided.
     * @param account_id
     * @return ResponseEntity<Account>
     */
    @GetMapping(ACCOUNTS_URI + ACCOUNTS_URI_PATH_VARIABLE)
    public ResponseEntity<Account> getAccount(@PathVariable(value="account_id") final Long account_id) {
        final Optional<Account> accountOpt =  accountService.getAccountById(account_id);
        return accountOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST API for saving the Account.
     * @param accountDto
     */
    @PostMapping(ACCOUNTS_URI)
    public void saveAccount(@Valid @RequestBody final AccountDto accountDto) {
        accountService.saveAccount(accountDto.toAccount());
    }

}
