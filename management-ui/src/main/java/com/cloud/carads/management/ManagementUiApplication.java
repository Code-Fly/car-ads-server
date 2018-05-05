package com.cloud.carads.management;

import com.cloud.carads.Scan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@EnableHystrix
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackageClasses = {Scan.class})
public class ManagementUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementUiApplication.class, args);
    }
}
