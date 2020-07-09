package com.wu.config;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration in which production environment will be excluded
 */
@Configuration
@EnableSwagger2
@Profile("!production")
public class SwaggerConfiguration {

    private static final String API_KEY_NAME = "x-auth-token";
    private static final String PASS_AS = "header";
    private static final String CONTROLLERS_BASE_PACKAGE = "com.wu.controller";

    @Value("${api.version}")
    private String version;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securitySchemes(securitySchemeList())
                .securityContexts(securityContextList())
                .select()
                .apis(RequestHandlerSelectors.basePackage(CONTROLLERS_BASE_PACKAGE))
                .build()
                .apiInfo(apiInfo());
    }

    private List<SecurityScheme> securitySchemeList() {
        return newArrayList(cookieAuthScheme());
    }

    // The Api Key for sending cookie in header
    private ApiKey cookieAuthScheme() {
        return new ApiKey(API_KEY_NAME, API_KEY_NAME, PASS_AS);
    }

    private List<SecurityContext> securityContextList() {
        return newArrayList(SecurityContext.builder().build());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Western Union Singularity APP API")
                .description("Western Union Telecom Service API Document in Spring Boot")
                .version(version)
                .build();
    }
}
