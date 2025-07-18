package com.java4rohit.accounts;


import com.java4rohit.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties(value ={AccountsContactInfoDto.class})
@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Account Microservice Rest API Documentation",
                description = "Axis Bank Accounts microservices Rest API Documentation ",
                version = "v1",
                contact = @Contact(
                        name = "Rohit Yadav",
                        email = "java4rohit@gmail.com",
                        url = "www.nowhere.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "www.nowhere.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Axis Bank Accounts microservices Rest API Documentation ",
                url = "http://localhost:8080/swagger-ui/index.html#"
        )
)

public class AccountsApplication {

    public static void main(String[] args) {

        SpringApplication.run(AccountsApplication.class, args);
    }

}
