package com.cloud.carads.account.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
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
    public Docket configSpringfoxDocketForWxApiUser() {
        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("帐号管理")
                .select()  // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.any()) // 对所有api进行监控
                .paths(PathSelectors.any()) // 对所有路径进行监控
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
