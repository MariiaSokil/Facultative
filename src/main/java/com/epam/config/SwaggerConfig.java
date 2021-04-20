package com.epam.config;


import java.util.ArrayList;
import java.util.List;

/*@Configuration
@EnableSwagger2*/
public class SwaggerConfig {
/*
    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api-v1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.epam.controllers"))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build();
    }

    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }*/
}
