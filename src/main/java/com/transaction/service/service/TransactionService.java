package com.transaction.service.service;

import com.transaction.service.dto.TransactionDto;
import com.transaction.service.model.Account;
import com.transaction.service.model.OperationType;
import com.transaction.service.model.Transaction;
import com.transaction.service.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.*;

import static com.transaction.service.constants.Constants.ACCOUNT_NOT_FOUND;
import static com.transaction.service.constants.Constants.OPERATION_NOT_FOUND;

/**
 * Service for managing Transactions.
 */
@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    OperationTypeService operationTypeService;

    /**
     * Saves the Transaction provided as method argument.
     * Validates the Account exists.
     * Validates the Operation Type exists.
     * Adjusts the Amount as positive or negative based on Operation Type.
     * @param transactionDto
     */
    @Transactional
    public void saveTransaction(TransactionDto transactionDto) {

        // Validate the Account exists.
        Optional<Account> accountOptional = accountService.getAccountById(transactionDto.getAccount_id());
        Account account = accountOptional.orElseThrow(()-> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ACCOUNT_NOT_FOUND);
        });

        // Validate the Operation Type exists.
        Optional<OperationType> operationTypeOptional = operationTypeService.getOperationTypeById(transactionDto.getOperation_type_id());
        OperationType operationType = operationTypeOptional.orElseThrow(()-> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, OPERATION_NOT_FOUND);
        });

        // Negate the amount based on Operation Type.
        Transaction transaction = transactionDto.toTransaction(account, operationType);
        if(operationType.isRegisterNegativeAmount()) {
            transaction.setAmount(transaction.getAmount().negate());
            transaction.setBalance(transaction.getAmount());
        } else {
            transaction.setBalance(transaction.getAmount());
            ArrayList<Transaction> previousTransactions = transactionRepository.findByAccountId(transaction.getAccount().getAccountId());
            for(Transaction t : previousTransactions) {
                if(t.getBalance().compareTo(new BigDecimal(0))==-1) {
                    if(t.getBalance().abs().compareTo(transaction.getBalance())==-1) {
                        transaction.setBalance(transaction.getBalance().subtract(t.getBalance().abs()));
                        t.setBalance(new BigDecimal(0));
                    } else {
                        t.setBalance(t.getBalance().add(transaction.getBalance()));
                        transaction.setBalance(new BigDecimal(0));
                        break;
                    }
                }
            }
            previousTransactions.forEach(t -> transactionRepository.save(t));
        }

        // Save the Transaction.
        transactionRepository.save(transaction);

    }

}
