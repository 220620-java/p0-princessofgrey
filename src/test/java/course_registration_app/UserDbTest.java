package course_registration_app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import course_registration_app.User;
import course_registration_app.UserDB;
import utils.ConnectionUtil;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@ExtendWith(MockitoExtension.class)
class UserDBTest {

    @Mock
    UserDB userDBMock;

    @Mock
    private Connection mockConnection =Mockito.mock(Connection.class);

    @Mock
    private Statement mockStatement =Mockito.mock(Statement.class);

    @Mock
    private ResultSet resultSet = Mockito.mock(ResultSet.class);

    @InjectMocks
    private static ConnectionUtil connectionUtil;

 
    public void setUp() throws Exception {
    	Mockito.mockStatic(UserDB.class);
        Mockito.when(connectionUtil.getConnection()).thenReturn(mockConnection);
    }

  
    @Test
   void testCreateUser() {
        // Setup
        final User user = new User("userName", "password", "firstName", "lastName", "address");
        Mockito.when(userDBMock.createUser(user)).thenReturn(true);
    }

    @Test
    void testLogin() {
        // Setup
        // Run the test
        // Setup
        final User user = new User("userName", "password", "firstName", "lastName", "address");
        Mockito.when(userDBMock.createUser(user)).thenReturn(true);

        Mockito.when(userDBMock.login("userName", "password")).thenReturn(user);

        // Verify the results
    }

    @Test
    void testLoginWithWrongPassWord() {
        // Setup
        // Run the test
        // Setup
        final User user = new User("userName", "password", "firstName", "lastName", "address");
        Mockito.when(userDBMock.createUser(user)).thenReturn(true);

        Mockito.when(userDBMock.login("userName", "wrong")).thenReturn(null);

        // Verify the results
    }


    @Test
    void testError_Log() {
        // Setup
        // Run the test
        UserDB.Error_Log("n");

        // Verify the results
    }
}
