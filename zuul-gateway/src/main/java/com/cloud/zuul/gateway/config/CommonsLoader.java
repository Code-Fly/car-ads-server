package com.cloud.zuul.gateway.config;

import com.cloud.carads.Scan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {Scan.class})
public class CommonsLoader {

}
