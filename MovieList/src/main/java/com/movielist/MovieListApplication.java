package com.movielist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieListApplication {

    public MovieListApplication() {
        // Default constructor
    }
    public static void main(String[] args) {
        SpringApplication.run(MovieListApplication.class, args);
    }
}
