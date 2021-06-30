package com.meeting.reservation.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class MeetingReservationApplication {

	@Bean
	public OpenAPI customOpenAPI(@Value("${application.description}") String description){
	    return new OpenAPI(). info(new Info()
                .title(description)
                .version("2.0")
                .termsOfService("http://swagger.io/terms")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));

    }

}