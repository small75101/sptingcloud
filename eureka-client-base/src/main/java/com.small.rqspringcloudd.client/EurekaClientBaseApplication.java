package com.small.rqspringcloudd.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class EurekaClientBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientBaseApplication.class, args);
    }

    @Autowired
    DiscoveryClient discoveryClient;

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        System.out.println(discoveryClient.description());
        System.out.println(discoveryClient.getServices());
        System.out.println(discoveryClient.getInstances("eurekaclientbase"));
        ServiceInstance localServiceInstance = discoveryClient.getLocalServiceInstance();
        System.out.println(localServiceInstance.getHost() + "   " + localServiceInstance.getServiceId());
        return "hi " + name + ",i am from port:" + port;
    }
}
