package com.transaction.service.service;

import com.transaction.service.model.Account;
import com.transaction.service.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(AccountService.class)
public class AccountServiceTests {

    @MockBean
    private AccountRepository accountRepositoryMock;

    @MockBean
    private Account accountMock;

    @Autowired
    AccountService accountService;

    @Test
    public void testGetAccountById() throws Exception {
        when(accountRepositoryMock.findById(1L)).thenReturn(Optional.of(accountMock));
        assertEquals(accountService.getAccountById(1L), Optional.of(accountMock));
    }

    @Test
    public void testSaveAccount() throws Exception {
        accountService.saveAccount(accountMock);
        verify(accountRepositoryMock, times(1)).save(any(Account.class));
    }

}
