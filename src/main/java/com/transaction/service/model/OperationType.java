package com.transaction.service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * JPA Entity for OperationType.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "operationType")
public class OperationType {

    /**
     * Stores the unique ID for each Operation Type.
     */
    @Id
    private int operationTypeId;

    /**
     * Stores the description for each Operation Type.
     */
    private String description;

    /**
     * Flag which indicates a negative or positive amount
     * should be registered for this operation type.
     * true: register a negative amount.
     * false: register a positive amount.
     */
    private boolean registerNegativeAmount;

    /**
     * Stores the Transactions associated with each Operation Type.
     * One To Many mapping as each Operation Type can be associated
     * with multiple Transactions.
     */
    @OneToMany(mappedBy = "operationType")
    private Set<Transaction> transactions;

}
