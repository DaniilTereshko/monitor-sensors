package com.tdi.sensorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SensorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SensorServiceApplication.class, args);
    }

}