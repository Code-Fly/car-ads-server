package com.cloud.carads.account.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

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
    @Value("${security.oauth2.client.client-id}")
    public String CLIENT_ID;

    @Value("${security.oauth2.client.client-secret}")
    public String CLIENT_SECRET;

    @Value("${security.oauth2.client.scope}")
    public String SCOPE;

    @Value("${security.oauth2.client.access-token-uri}")
    public String ACCESS_TOKEN_URI;

    @Value("${security.oauth2.client.user-authorization-uri}")
    public String USER_AUTHORIZATION_URI;

//    @Bean
//    public SecurityConfiguration security() {
//        return new SecurityConfiguration(CLIENT_ID,
//                CLIENT_SECRET,
//                "realm", "OAUTH_REALM",
//                "apiKey", ApiKeyVehicle.HEADER, "api_key", "");
//    }


    @Bean
    public SecurityConfiguration security() {


        return SecurityConfigurationBuilder.builder()
                .clientId(CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .realm("OAUTH_REALM")
                .build();
    }

    @Bean
    public Docket configSpringfoxDocketForApiUser() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cloud.carads.account"))
                .paths(PathSelectors.any())
                .build();
//                .securitySchemes(Arrays.asList(securityScheme()))
//                .securityContexts(Arrays.asList(securityContext()));
    }

//    @Bean
//    public Docket configSpringfoxDocketForApiArea() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("area management")
//                .apiInfo(apiInfo())
//                .forCodeGeneration(true)
//                .select()
//                .paths(PathSelectors.regex("/area/.*"))
//                .build();
//    }
//
//    @Bean
//    public Docket configSpringfoxDocketForApiCapp() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("dictionary")
//                .apiInfo(apiInfo())
//                .forCodeGeneration(true)
//                .select()
//                .paths(PathSelectors.regex("/capp/.*"))
//                .build();
//    }
//
//    @Bean
//    public Docket configSpringfoxDocketForApiSms() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("sms")
//                .apiInfo(apiInfo())
//                .forCodeGeneration(true)
//                .select()
//                .paths(PathSelectors.regex("/sms/.*"))
//                .build();
//    }


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

    private SecurityScheme securityScheme() {
        GrantType grantType = new AuthorizationCodeGrantBuilder()
                .tokenEndpoint(new TokenEndpoint(ACCESS_TOKEN_URI, "access_token"))
                .tokenRequestEndpoint(
                        new TokenRequestEndpoint(USER_AUTHORIZATION_URI, CLIENT_ID, CLIENT_SECRET))
                .build();

        SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
                .grantTypes(Arrays.asList(grantType))
                .scopes(Arrays.asList(scopes()))
                .build();
        return oauth;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(new SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.regex("/account/.*"))
                .build();
    }


    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = {
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations"),
                new AuthorizationScope("app", "Access foo API")};
        return scopes;
    }

}
