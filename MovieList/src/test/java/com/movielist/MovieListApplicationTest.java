package com.movielist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MovieListApplicationTest {

    @Test
    void mainMethodShouldNotThrowException() {
        assertDoesNotThrow(() -> {
            MovieListApplication.main(new String[]{"--spring.main.web-application-type=none"});
        });
    }
}
