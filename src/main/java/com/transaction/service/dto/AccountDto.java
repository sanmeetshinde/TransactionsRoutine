package com.transaction.service.dto;

import com.transaction.service.model.Account;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for Account.
 * Used for data exchange between AccountController and calling services.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    /**
     * Stores the Document Number for each account.
     * Mandatory field.
     */
    @NotBlank(message = "The document_number is required.")
    private String document_number;

    /**
     * Converts AccountDto to Account.
     * @return Account
     */
    public Account toAccount() {
        return Account.builder()
                .documentNumber(document_number)
                .build();
    }

}
