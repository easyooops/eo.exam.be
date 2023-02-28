package com.easyoops.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Slf4j
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private ApiKey apiKey() {
        return new ApiKey("access_token", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("access_token", authorizationScopes));
    }

    @Bean
    public Docket apiV1() {
        String version = "v1";

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo(version))
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(String version) {
        String title = "API " + version;
        String description = "How to use access_token in a header\n" +
                "1) Click the 'Authorize' button on the right side\n" +
                "2) Enter access token information in the value\n" +
                "  *format : {grantType} {accessToken}\n" +
                "  *ex) Bearer eyJ∙∙.eyJz∙∙._U4Bhy∙∙";

        return new ApiInfoBuilder()
                .version(version)
                .title(title)
                .description(description)
                .build();
    }

}
