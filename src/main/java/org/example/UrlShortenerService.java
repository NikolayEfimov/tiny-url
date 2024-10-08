package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UrlShortenerService {

    public static final int MAX_URL_COUNT = 100;
    private Map<String, String> shortToLongMap;
    private Map<String, String> longToShortMap;
    private String base;
    private ShorteningStrategy shorteningStrategy;
    
    public UrlShortenerService(ShorteningStrategy shorteningStrategy) {
        this.shortToLongMap = new ConcurrentHashMap<>();
        this.longToShortMap = new ConcurrentHashMap<>();
        this.base = "http://tyne.com/";
        this.shorteningStrategy = shorteningStrategy;
    }
    
    public String shorten(String longUrl) {
        if (shortToLongMap.size() > MAX_URL_COUNT) {
            throw new MaxUrlCountExceedException();
        }

        if (longToShortMap.containsKey(longUrl)) {
            return longToShortMap.get(longUrl);
        }

        String shortUrl;
        do {
            shortUrl = shorteningStrategy.shorten(longUrl, base);
        } while (shortToLongMap.containsKey(shortUrl));

        shortToLongMap.put(shortUrl, longUrl);
        longToShortMap.put(longUrl, shortUrl);
        return shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        return shortToLongMap.get(shortUrl);
    }

    public String getShortUrl(String shortUrl) {
        return longToShortMap.get(shortUrl);
    }

}
