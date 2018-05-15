package com.cloud.carads.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Administrator on 2016/10/13.
 */
/*
 * Restful API 访问路径:
 * http://IP:port/{context-path}/swagger-ui.html
 * eg:http://localhost:8080/jd-config-web/swagger-ui.html
 */
@EnableSwagger2
@Configuration
public class RestApiConfig {

    @Bean
    public Docket configSpringfoxDocketForApiUser() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Account management")
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .select()
                .paths(PathSelectors.regex("/account/.*"))
                .build();
    }

    @Bean
    public Docket configSpringfoxDocketForApiArea() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("area management")
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .select()
                .paths(PathSelectors.regex("/area/.*"))
                .build();
    }

    @Bean
    public Docket configSpringfoxDocketForApiCapp() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("dictionary")
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .select()
                .paths(PathSelectors.regex("/capp/.*"))
                .build();
    }

    @Bean
    public Docket configSpringfoxDocketForApiSms() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("sms")
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .select()
                .paths(PathSelectors.regex("/sms/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("帐号管理")
                .description("前端API Doc")
                .version("3.0.0")
//                .termsOfServiceUrl("http://terms-of-services.url")
//                .license("LICENSE")
//                .licenseUrl("http://url-to-license.com")
                .build();
    }

}
