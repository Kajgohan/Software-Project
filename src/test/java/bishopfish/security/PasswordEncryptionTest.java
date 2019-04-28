package bishopfish.security;

import bishopfish.db.DBMI;
import bishopfish.db.DBUpdater;
import bishopfish.utils.EncryptPassword;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static bishopfish.utils.EncryptPassword.encryptEmployee;
import static bishopfish.utils.EncryptPassword.verifyEmployee;
import static org.junit.jupiter.api.Assertions.*;


public class PasswordEncryptionTest {
    @BeforeEach
    public void setup(){
        DBUpdater dbu = new DBUpdater(DBMI.EmployeeLogins.value);
        dbu.dropTable();
        dbu.createTable();
    }

    @Test
    public void testVerifyTrue(){
        EncryptPassword.encryptEmployee("12345678", "HUNTER2", "");
        assertTrue(verifyEmployee("12345678", "HUNTER2"));
    }

    @Test
    public void testVerifyFalse(){
        EncryptPassword.encryptEmployee("12345678", "HUNTER2", "");
        assertFalse(verifyEmployee("12345678", "HUNTER1"));
    }

    @Test
    public void retestVerifyTrue(){
        EncryptPassword.encryptEmployee("Staff", "Staff", "");
        assertTrue(verifyEmployee("Staff", "Staff"));
    }

    @Test
    public void retestVerifyFalse(){
        EncryptPassword.encryptEmployee("OUTLOOK1", "PEANUT", "");
        assertFalse(verifyEmployee("OUTLOOK1", "PEENUT"));
    }
}
