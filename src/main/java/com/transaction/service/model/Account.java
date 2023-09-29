package com.transaction.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * JPA Entity for Account.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "account")
public class Account {

    /**
     * Stores the unique ID for each Account.
     * Auto-generated field.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long accountId;

    /**
     * Stores the Document Number for each Account.
     */
    private String documentNumber;

    /**
     * Stores the Transactions associated with each Account.
     * One To Many mapping as each Account can have multiple Transactions.
     */
    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private Set<Transaction> transactions;

}
