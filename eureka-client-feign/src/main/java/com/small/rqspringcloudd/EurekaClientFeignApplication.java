package com.small.rqspringcloudd;

import com.netflix.loadbalancer.IRule;
import com.small.rqspringcloudd.rule.MyIPRandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EurekaClientFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientFeignApplication.class, args);
    }

    //@Bean
    public IRule ribbonRule() {
        return new MyIPRandomRule();
    }
}
