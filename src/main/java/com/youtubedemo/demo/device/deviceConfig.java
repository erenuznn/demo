package com.youtubedemo.demo.device;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class deviceConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            deviceRepository repository){
        return args -> {
            device   iphone = new device(
                    "Apple",
                    "iPhone 5s",
                    "ios",
                    "12"
            );

            device  iphone2 = new device(
                    "Apple",
                    "iPhone 4s",
                    "ios",
                    "11"
            );

            device   iphone3 = new device(
                    "Apple",
                    "iPhone 6s",
                    "ios",
                    "10"
            );

            repository.saveAll(
                    List.of(iphone,iphone2,iphone3)
            );
        };

    }
}

