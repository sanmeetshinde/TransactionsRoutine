package com.transaction.service.service;

import com.transaction.service.dto.TransactionDto;
import com.transaction.service.model.Account;
import com.transaction.service.model.OperationType;
import com.transaction.service.model.Transaction;
import com.transaction.service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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
        }

        // Save the Transaction.
        transactionRepository.save(transaction);

    }

}
