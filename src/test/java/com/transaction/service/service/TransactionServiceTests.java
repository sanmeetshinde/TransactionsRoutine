package com.transaction.service.service;

import com.transaction.service.dto.TransactionDto;
import com.transaction.service.model.Account;
import com.transaction.service.model.OperationType;
import com.transaction.service.model.Transaction;
import com.transaction.service.repository.AccountRepository;
import com.transaction.service.repository.TransactionRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(TransactionService.class)
public class TransactionServiceTests {

    @MockBean
    private TransactionRepository transactionRepositoryMock;

    @MockBean
    private AccountService accountServiceMock;

    @MockBean
    private OperationTypeService operationTypeServiceMock;

    @MockBean
    private TransactionDto transactionDtoMock;

    @MockBean
    private Account accountMock;

    @MockBean
    private OperationType operationTypeMock;

    @Autowired
    TransactionService transactionService;

    @Test
    public void testSaveTransaction_success() throws Exception {
        when(accountServiceMock.getAccountById(any(Long.class))).thenReturn(Optional.of(accountMock));
        when(operationTypeServiceMock.getOperationTypeById(any(Integer.class))).thenReturn(Optional.of(operationTypeMock));
        transactionService.saveTransaction(transactionDtoMock);
        verify(transactionRepositoryMock, times(1)).save(any());
    }

    @Test
    public void testSaveTransaction_accountNotFound() throws Exception {
        when(accountServiceMock.getAccountById(any(Long.class))).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> transactionService.saveTransaction(transactionDtoMock));
        verify(transactionRepositoryMock, times(0)).save(any());
    }

    @Test
    public void testSaveTransaction_operationNotFound() throws Exception {
        when(accountServiceMock.getAccountById(any(Long.class))).thenReturn(Optional.of(accountMock));
        when(operationTypeServiceMock.getOperationTypeById(any(Integer.class))).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> transactionService.saveTransaction(transactionDtoMock));
        verify(transactionRepositoryMock, times(0)).save(any());
    }

    @Test
    public void testSaveTransaction_negativeAmount() throws Exception {
        TransactionDto transactionDto = new TransactionDto(1L, 1, 1.0D);
        OperationType operationType = new OperationType(1, "Operation 1", true, new HashSet<Transaction>());
        when(accountServiceMock.getAccountById(any(Long.class))).thenReturn(Optional.of(accountMock));
        when(operationTypeServiceMock.getOperationTypeById(any(Integer.class))).thenReturn(Optional.of(operationType));
        transactionService.saveTransaction(transactionDto);
        verify(transactionRepositoryMock).save(argThat((Transaction transaction) -> transaction.getAmount().equals(new BigDecimal(-1.0))));
    }

    @Test
    public void testSaveTransaction_positiveAmount() throws Exception {
        TransactionDto transactionDto = new TransactionDto(1L, 1, 1.0D);
        OperationType operationType = new OperationType(1, "Operation 1", false, new HashSet<Transaction>());
        when(accountServiceMock.getAccountById(any(Long.class))).thenReturn(Optional.of(accountMock));
        when(operationTypeServiceMock.getOperationTypeById(any(Integer.class))).thenReturn(Optional.of(operationType));
        transactionService.saveTransaction(transactionDto);
        verify(transactionRepositoryMock).save(argThat((Transaction transaction) -> transaction.getAmount().equals(new BigDecimal(1.0))));
    }

}
