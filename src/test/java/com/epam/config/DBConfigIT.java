package com.epam.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.epam.repository")
@ActiveProfiles("test")
//@PropertySource ("persistence-generic-entity.properties")
@EnableTransactionManagement
public class DBConfigIT {

}
