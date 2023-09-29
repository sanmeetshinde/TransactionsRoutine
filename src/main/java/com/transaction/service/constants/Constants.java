package com.transaction.service.constants;

public class Constants {

    //URI for GET API to retrieve an Account, POST API to save an Account.
    public static final String ACCOUNTS_URI = "/accounts";
    // URI for Path Variable in GET API to retrieve an Account.
    public static final String ACCOUNTS_URI_PATH_VARIABLE = "/{account_id}";
    // URI for POST API to save a Transaction.
    public static final String TRANSACTIONS_URI = "/transactions";
    // Response message for Account not found.
    public static final String ACCOUNT_NOT_FOUND = "Account not found.";
    // Response message for Operation Type not found.
    public static final String OPERATION_NOT_FOUND = "Operation Type not found.";
    // Description for Operation Type: Normal Purchase
    public static final String NORMAL_PURCHASE_DESCRIPTION = "Normal Purchase";
    // Description for Operation Type: Installment Purchase
    public static final String INSTALLMENT_PURCHASE_DESCRIPTION = "Purchase with installments";
    // Description for Operation Type: Withdrawal
    public static final String WITHDRAWAL_DESCRIPTION = "Withdrawal";
    // Description for Operation Type: Credit Voucher
    public static final String CREDIT_VOUCHER_DESCRIPTION = "Credit Voucher";

}
