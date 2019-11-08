package com.contactabilidad.push;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.blockhound.BlockHound;

import reactor.core.scheduler.ReactorBlockHoundIntegration;

@SpringBootApplication
public class PushApplication {

    public static void main(String[] args) {
        BlockHound.install();
        SpringApplication.run(PushApplication.class, args);

    }

}
