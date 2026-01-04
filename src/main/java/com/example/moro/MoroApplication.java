package com.example.moro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MoroApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoroApplication.class, args);
    }

}
