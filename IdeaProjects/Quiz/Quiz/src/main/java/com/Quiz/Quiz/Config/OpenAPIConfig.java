package com.Quiz.Quiz.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customerAPI(){
        return new OpenAPI().info(
                new Info()
                        .title("Crud Application")
                        .version("0.1")
                        .description("Swagger for crud APIs")



        );

    }
//    @Bean
//    @Profile("test")
//    public GroupedOpenApi devApi() {
//        return GroupedOpenApi.builder()
//                .group("user")
//                .pathsToMatch("/api/user/**")
//                .build();
//    }
//
//    @Bean
//    @Profile("test")
//    public GroupedOpenApi prodApi() {
//        return GroupedOpenApi.builder()
//                .group("questions")
//                .pathsToMatch("/api/questions/**")
//                .build();
//}


}
