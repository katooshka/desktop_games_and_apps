package avatar_generator;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Author: katooshka
 * Date: 11/8/15.
 */
public class HashGetter {
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(generateHash("zabik123@yandex.ru"));
        System.out.println(generateHash("MyEmailAddress@example.com "));
    }
    public static String generateHash(String initialString) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        initialString = initialString.trim().toLowerCase();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        md.update(initialString.getBytes("utf-8"));
        String resultString = new BigInteger(1, md.digest()).toString(16);
        while (resultString.length() < 32) {
            resultString = "0" + resultString;
        }
        return resultString;
    }

}
