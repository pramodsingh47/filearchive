package com.filedm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "File Upload API", version = "1.0", description = "File upload service"))
@SecurityScheme(name = "Authorization", scheme = "basic", type = SecuritySchemeType.APIKEY,  in = SecuritySchemeIn.HEADER )
public class ArchiveManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArchiveManagmentApplication.class, args);
	}

}
