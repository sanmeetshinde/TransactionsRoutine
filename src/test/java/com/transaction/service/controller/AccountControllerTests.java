package com.transaction.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction.service.dto.AccountDto;
import com.transaction.service.model.Account;
import com.transaction.service.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.transaction.service.constants.Constants.ACCOUNTS_URI;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountServiceMock;

    @MockBean
    private Account accountMock;

    @MockBean
    private AccountDto accountDtoMock;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetAccount_AccountFound() throws Exception {
        when(accountServiceMock.getAccountById(1L)).thenReturn(Optional.of(accountMock));
        this.mockMvc.perform(get(ACCOUNTS_URI + "/1")).andExpect(status().isOk());
    }

    @Test
    public void testGetAccount_AccountNotFound() throws Exception {
        when(accountServiceMock.getAccountById(1L)).thenReturn(Optional.empty());
        this.mockMvc.perform(get(ACCOUNTS_URI + "/1")).andExpect(status().isNotFound());
    }

    @Test
    public void testGetAccount_InvalidPathAttributeString() throws Exception {
        this.mockMvc.perform(get(ACCOUNTS_URI + "/test")).andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetAccount_InvalidPathAttributeFloat() throws Exception {
        this.mockMvc.perform(get(ACCOUNTS_URI + "/1.0")).andExpect(status().is4xxClientError());
    }

    @Test
    public void testSaveAccount_success() throws Exception {
        AccountDto accountDto = new AccountDto("123");
        this.mockMvc.perform(post(ACCOUNTS_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountDto)))
                .andExpect(status().isOk());
        verify(accountServiceMock, times(1)).saveAccount(any(Account.class));
    }

    @Test
    public void testSaveAccount_invalidInput() throws Exception {
        AccountDto accountDto = new AccountDto(null);
        this.mockMvc.perform(post(ACCOUNTS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountDto)))
                .andExpect(status().isBadRequest());
        verify(accountServiceMock, times(0)).saveAccount(any(Account.class));
    }

}
