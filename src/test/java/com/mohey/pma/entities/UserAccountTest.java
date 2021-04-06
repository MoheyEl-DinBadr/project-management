package com.mohey.pma.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UserAccountTest {
    @Test
    public void testSetUserId() {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(123L);
        assertEquals(123L, userAccount.getUserId());
    }

    @Test
    public void testSetUserName() {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserName("janedoe");
        assertEquals("janedoe", userAccount.getUserName());
    }

    @Test
    public void testSetEmail() {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail("jane.doe@example.org");
        assertEquals("jane.doe@example.org", userAccount.getEmail());
    }

    @Test
    public void testSetPassword() {
        UserAccount userAccount = new UserAccount();
        userAccount.setPassword("iloveyou");
        assertEquals("iloveyou", userAccount.getPassword());
    }

    @Test
    public void testSetRole() {
        UserAccount userAccount = new UserAccount();
        userAccount.setRole("Role");
        assertEquals("Role", userAccount.getRole());
    }

    @Test
    public void testSetEnabled() {
        UserAccount userAccount = new UserAccount();
        userAccount.setEnabled(true);
        assertTrue(userAccount.isEnabled());
    }

    @Test
    public void testToString() {
        assertEquals("UserAccount{userName='null', email='null', password='null', role='null', enabled=true}",
                (new UserAccount()).toString());
    }
}

