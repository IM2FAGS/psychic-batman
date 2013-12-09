package abey.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author nicolas
 */
public class Salt {
    
    public static String newSalt() {
        final SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return toHex(salt);
    }
    
    private static String toHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        String hex = bi.toString(16);
        int paddingLength = (bytes.length * 2) - hex.length();
        if(paddingLength > 0) 
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }
    
    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        messageDigest.update((password + salt).getBytes());
        String hash = toHex(messageDigest.digest());
        return hash;
    }
    
    public static boolean checkPassword(String password, String salt, String encryptedPassword) throws NoSuchAlgorithmException {
        return hashPassword(password, salt).equals(encryptedPassword);
    }
    
}
