package com.chen.sboot.loginconsumer.controller;

import DTO.UserDTO;
import com.netflix.loadbalancer.IRule;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: 陈龙
 * @Date: 2019/3/1 14:20
 * @Version 1.0
 */

@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {
    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;
    private final LoadBalancerClient loadBalancerClient;

    @Autowired
    public UserController(RestTemplate restTemplate, DiscoveryClient discoveryClient, LoadBalancerClient loadBalancerClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
        this.loadBalancerClient = loadBalancerClient;
    }

    @GetMapping("/getUserById/{id}")
    private String getUserById(@PathVariable("id") Long id){
        ServiceInstance serviceInstance = loadBalancerClient.choose("login-provider");
        UserDTO userDTO=restTemplate.getForObject("http://login-provider/user/getUserById/"+id, UserDTO.class);
        log.info("{}:{}:{}",serviceInstance.getHost(),serviceInstance.getPort());
        return userDTO.toString();
    }
}
