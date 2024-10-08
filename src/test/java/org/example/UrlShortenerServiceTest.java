package org.example;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class UrlShortenerServiceTest {

    ShorteningStrategy strategy = new RandomShorteningStrategy();

    UrlShortenerService urlShortenerService = new UrlShortenerService(strategy);

    @Test
    public void shorten() {
        var longUrl = "https://example.net";

        var shortUrl = urlShortenerService.shorten(longUrl);

        assertThat(urlShortenerService.getOriginalUrl(shortUrl)).isEqualTo(longUrl);
    }
}
