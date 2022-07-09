package course_registration_app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import course_registration_app.Account;
import course_registration_app.AccountDB;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class AccountDBTest {

    @Mock
    AccountDB accountDBMock;

    @Test
    void testCreateAccount() {
        // Setup
        final Account account = new Account(0, "accountType", "accountNumber0", 0.0);
        Mockito.when(accountDBMock.createAccount(account)).thenReturn(true);
    }

    @Test
    void testDeposit() {
        final Account account = new Account(0, "accountType", "accountNumber1", 0.0);
        Mockito.when(accountDBMock.createAccount(account)).thenReturn(true);
        Mockito.when(accountDBMock.deposit("accountNumber1", 100.0)).thenReturn(100.0);
        Mockito.when(accountDBMock.deposit("accountNumber1", 100.0)).thenReturn(200.0);
    }

    @Test
    void testGetBalance() {
        final Account account = new Account(0, "accountType", "accountNumber2", 0.0);
        Mockito.when(accountDBMock.createAccount(account)).thenReturn(true);
        Mockito.when(accountDBMock.deposit("accountNumber2", 100.0)).thenReturn(100.0);
        Mockito.when(accountDBMock.getBalance("accountNumber2")).thenReturn(100.0);
    }

    @Test
    void testWithdraw() {
        final Account account = new Account(0, "accountType", "accountNumber2", 0.0);
        Mockito.when(accountDBMock.createAccount(account)).thenReturn(true);
        Mockito.when(accountDBMock.deposit("accountNumber2", 100.0)).thenReturn(100.0);
        Mockito.when(accountDBMock.withdraw("accountNumber2", 50.0)).thenReturn(50.0);
    }
}
