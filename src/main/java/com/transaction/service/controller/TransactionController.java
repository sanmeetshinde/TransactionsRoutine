package com.transaction.service.controller;

import com.transaction.service.dto.TransactionDto;
import com.transaction.service.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.transaction.service.constants.Constants.TRANSACTIONS_URI;

/**
 * Rest Controller for Transactions.
 */
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * POST API for saving a Transaction.
     * @param transactionDto
     */
    @PostMapping(TRANSACTIONS_URI)
    public void saveTransaction(@Valid @RequestBody final TransactionDto transactionDto) {
        transactionService.saveTransaction(transactionDto);
    }

}