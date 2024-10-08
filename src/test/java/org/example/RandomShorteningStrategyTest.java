package org.example;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import org.junit.jupiter.api.Test;

class RandomShorteningStrategyTest {

    ShorteningStrategy strategy = new RandomShorteningStrategy();

    @Test
    void shorten() {
        String longUrl = "http://example.com";
        String baseURl = "http://tiny/";

        var shortUrl1 = strategy.shorten(longUrl, baseURl);
        var shortUrl2 = strategy.shorten(longUrl, baseURl);
        var shortUrl3 = strategy.shorten(longUrl, baseURl);

        assertThat(shortUrl1).isNotEqualTo(shortUrl2);
        assertThat(shortUrl2).isNotEqualTo(shortUrl3);
        assertThat(shortUrl3).isNotEqualTo(shortUrl1);
    }

    @Test
    void shortenStrategyGenerateRandomUrls() {
        String longUrl = "http://example.com";
        String baseURl = "http://tiny/";

        var numberOfUrls = 10_000;
        var generated = new HashSet<String>();
        for (int i = 0; i < numberOfUrls; i++) {
            generated.add(strategy.shorten(longUrl, baseURl));
        }
        assertThat(generated).hasSize(numberOfUrls);
    }
}
