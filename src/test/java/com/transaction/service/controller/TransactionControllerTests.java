package com.transaction.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction.service.dto.TransactionDto;
import com.transaction.service.model.Account;
import com.transaction.service.model.Transaction;
import com.transaction.service.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static com.transaction.service.constants.Constants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
public class TransactionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionServiceMock;

    @MockBean
    private Transaction transactionMock;

    @MockBean
    private TransactionDto transactionDtoMock;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSaveTransaction_success() throws Exception {
        TransactionDto transactionDto = new TransactionDto(1L, 1, 1.0D);
        this.mockMvc.perform(post(TRANSACTIONS_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionDto)))
                .andExpect(status().isOk());
        verify(transactionServiceMock, times(1)).saveTransaction(any(TransactionDto.class));
    }

    @Test
    public void testSaveAccount_nullAccount() throws Exception {
        TransactionDto transactionDto = new TransactionDto(null, 1, 1.0D);
        this.mockMvc.perform(post(TRANSACTIONS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDto)))
                .andExpect(status().isBadRequest());
        verify(transactionServiceMock, times(0)).saveTransaction(any(TransactionDto.class));
    }

    @Test
    public void testSaveAccount_accountNotFound() throws Exception {
        TransactionDto transactionDto = new TransactionDto(1L, 1, 1.0D);
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, ACCOUNT_NOT_FOUND))
                .when(transactionServiceMock).saveTransaction(any(TransactionDto.class));
        this.mockMvc.perform(post(TRANSACTIONS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDto)))
                .andExpect(status().isNotFound());
        verify(transactionServiceMock, times(1)).saveTransaction(any(TransactionDto.class));
    }

    @Test
    public void testSaveAccount_nullOperation() throws Exception {
        TransactionDto transactionDto = new TransactionDto(1L, null, 1.0D);
        this.mockMvc.perform(post(TRANSACTIONS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDto)))
                .andExpect(status().isBadRequest());
        verify(transactionServiceMock, times(0)).saveTransaction(any(TransactionDto.class));
    }

    @Test
    public void testSaveAccount_operationNotFound() throws Exception {
        TransactionDto transactionDto = new TransactionDto(1L, 1, 1.0D);
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, OPERATION_NOT_FOUND))
                .when(transactionServiceMock).saveTransaction(any(TransactionDto.class));
        this.mockMvc.perform(post(TRANSACTIONS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDto)))
                .andExpect(status().isNotFound());
        verify(transactionServiceMock, times(1)).saveTransaction(any(TransactionDto.class));
    }

    @Test
    public void testSaveAccount_nullAmount() throws Exception {
        TransactionDto transactionDto = new TransactionDto(1L, 1, null);
        this.mockMvc.perform(post(TRANSACTIONS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionDto)))
                .andExpect(status().isBadRequest());
        verify(transactionServiceMock, times(0)).saveTransaction(any(TransactionDto.class));
    }

}
