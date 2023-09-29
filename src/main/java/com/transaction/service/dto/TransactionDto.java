package com.transaction.service.dto;

import com.transaction.service.model.Account;
import com.transaction.service.model.OperationType;
import com.transaction.service.model.Transaction;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO for Transaction.
 * Used for data exchange between TransactionController and calling services.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    /**
     * Stores the Account ID for each Transaction.
     * Mandatory field.
     */
    @NotNull(message = "The account_id is required.")
    private Long account_id;

    /**
     * Stores the Operation Type ID for each Transaction.
     * Mandatory field.
     */
    @NotNull(message = "The operation_type_id is required.")
    private Integer operation_type_id;

    /**
     * Stores the Amount for each Transaction.
     * Mandatory field.
     */
    @NotNull(message = "The amount is required.")
    private Double amount;

    /**
     * Converts TransactionDto to Transaction.
     * @param account
     * @param operationType
     * @return Transaction
     */
    public Transaction toTransaction(Account account, OperationType operationType) {
        return Transaction.builder()
                .account(account)
                .operationType(operationType)
                .amount(new BigDecimal(Math.abs(amount)))
                .build();
    }

}
