package com.pocket.police;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PoliceInMyPocketBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoliceInMyPocketBackendApplication.class, args);
    }

}
