package com.transaction.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * JPA Entity for Transaction.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transaction")
public class Transaction {

    /**
     * Stores the unique ID for each Transaction.
     * Auto-generated field.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long transactionId;

    /**
     * Stores the Account for each Transaction.
     * Many To One mapping as many Transactions can be
     * mapped to one Account.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountId", referencedColumnName = "accountId")
    private Account account;

    /**
     * Stores the Operation Type for each Transaction.
     * Many To One mapping as many Transactions can be
     * mapped to one Operation Type.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "operationTypeId", referencedColumnName = "operationTypeId")
    private OperationType operationType;

    /**
     * Stores the Amount for each Transaction.
     */
    private BigDecimal amount;

    /**
     * Stores the Balance for each Transaction.
     */
    @JsonIgnore
    private BigDecimal balance;

    /**
     * Stores the Timestamp for each Transaction.
     * Auto-populated during insertion of a record.
     */
    @CreationTimestamp
    private Instant eventDate;

}
