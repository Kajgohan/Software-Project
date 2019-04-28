package bishopfish.utils;

import bishopfish.db.DBBuffer;
import bishopfish.db.DBCustom;
import bishopfish.db.DBMI;
import bishopfish.db.DBUpdater;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class EncryptPassword {
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    private static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public static String generateSecurePassword(String password, String salt) {
        String returnValue = null;
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

        returnValue = Base64.getEncoder().encodeToString(securePassword);

        return returnValue;
    }

    public static boolean verifyUserPassword(String providedPassword,
                                             String securedPassword, String salt)
    {

        // Generate New secure password with the same salt
        String newSecurePassword = generateSecurePassword(providedPassword, salt);
                //.replace("=", "");
      //  System.out.println("Verification Password: " + generateSecurePassword(providedPassword, salt));
        // Check if two passwords are equal
        //was .equalsIgnoreCase
        Boolean returnValue = newSecurePassword.equals(securedPassword);

        return returnValue;
    }
    public static String getSalt(String msg) {
        String result = "";
        int i;
        int charSum = 0;
        for (Character c : msg.toCharArray()) {
            charSum += (int) c;
        }
        for (i = 0; i < 23; i++) {
            result += ((charSum + ((charSum) << (3 + i))) % (126 - 32 + 1)) + 32;
        }
        return result;
    }


    public static void main(String[] args){
        EncryptPassword E = new EncryptPassword();
       // System.out.println(generateSecurePassword("password", getSalt("password")));
    }

    public static void encryptEmployee(String employeeID, String password, String permissions){
        DBBuffer<EmployeeLoginEntry> dbbLogins = new DBBuffer<>(DBMI.EmployeeLogins.value);
        String securePassword = generateSecurePassword(password, getSalt(employeeID));
        EmployeeLoginEntry encryptedEntry = new EmployeeLoginEntry(employeeID, securePassword, permissions);
        dbbLogins.add(encryptedEntry);
        dbbLogins.close();
    }

    public static boolean verifyEmployee(String employeeID, String passIn){
        String salt = getSalt(employeeID);
       // System.out.println(salt);
      //  System.out.println("Verification Returning" + DBCustom.getSaltedPassword(employeeID));
        String probe = DBCustom.getSaltedPassword(employeeID);
       // System.out.println("Probe: " + probe);
        return verifyUserPassword(passIn, probe, salt);
    }
}
