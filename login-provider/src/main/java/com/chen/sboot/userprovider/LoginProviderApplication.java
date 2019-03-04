package com.chen.sboot.userprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LoginProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginProviderApplication.class, args);
    }

}
