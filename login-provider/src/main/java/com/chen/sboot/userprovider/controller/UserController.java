package com.chen.sboot.userprovider.controller;

import DO.UserDO;
import DTO.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 陈龙
 * @Date: 2019/3/1 10:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final DiscoveryClient discoveryClient;

    @Autowired
    public UserController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/getServers")
    private String getServers(){
        List<ServiceInstance> instances = discoveryClient.getInstances("user-provider");
        instances.forEach(System.out::print);
        return "success";
    }

    @GetMapping("/getUserById/{id}")
    private UserDTO getUserById(@PathVariable("id") Long id){
        UserDO userDO=new UserDO();
        userDO.setId((long) 1);
        userDO.setName("张三");
        userDO.setPhone("110");
        UserDTO userDTO=new UserDTO();
        BeanUtils.copyProperties(userDO,userDTO);
        return userDTO;
    }

}
