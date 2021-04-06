package com.mohey.pma.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mohey.pma.dao.UserAccountRepository;
import com.mohey.pma.entities.UserAccount;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserAccountService.class, BCryptPasswordEncoder.class})
@ExtendWith(SpringExtension.class)
public class UserAccountServiceTest {
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountService userAccountService;

    @Test
    public void testFindAll() {
        ArrayList<UserAccount> userAccountList = new ArrayList<UserAccount>();
        when(this.userAccountRepository.findAll()).thenReturn(userAccountList);
        List<UserAccount> actualFindAllResult = this.userAccountService.findAll();
        assertSame(userAccountList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(this.userAccountRepository).findAll();
    }

    @Test
    public void testSave() {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail("jane.doe@example.org");
        userAccount.setPassword("iloveyou");
        userAccount.setUserName("janedoe");
        userAccount.setRole("Role");
        userAccount.setUserId(123L);
        userAccount.setEnabled(true);
        when(this.userAccountRepository.save((UserAccount) any())).thenReturn(userAccount);
        when(this.bCryptPasswordEncoder.encode((CharSequence) any())).thenReturn("foo");
        UserAccount userAccount1 = new UserAccount();
        this.userAccountService.save(userAccount1);
        verify(this.bCryptPasswordEncoder).encode((CharSequence) any());
        verify(this.userAccountRepository).save((UserAccount) any());
        assertEquals("foo", userAccount1.getPassword());
    }
}

