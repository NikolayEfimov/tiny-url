package org.example;

import java.util.Random;

public class RandomShorteningStrategy implements ShorteningStrategy {

    private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final int MAX_LENGTH = 6;

    @Override
    public String shorten(String longUrl, String baseDomain) {
        var random = new Random();
        var shortUrl = new StringBuilder(baseDomain);

        for (int i = 0; i < MAX_LENGTH; i++) {
            shortUrl.append(CHAR_SET.charAt(random.nextInt(CHAR_SET.length())));
        }
        return shortUrl.toString();
    }
}
