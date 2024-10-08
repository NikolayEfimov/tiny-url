package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class HashingShorteningStrategy implements ShorteningStrategy {


    @Override
    public String shorten(String longUrl, String baseDomain) {
        try {
            var messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(longUrl.getBytes());
            byte[] digest = messageDigest.digest();

            var shortUrl = new StringBuilder(baseDomain);

            for (byte b: digest) {
                shortUrl.append(String.format("%02x", b & 0xff));
            }

            return shortUrl.substring(0, baseDomain.length() + 6);
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 was not found");
        }
    }
}
