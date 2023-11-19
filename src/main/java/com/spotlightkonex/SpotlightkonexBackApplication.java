package com.spotlightkonex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpotlightkonexBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpotlightkonexBackApplication.class, args);
    }

}
