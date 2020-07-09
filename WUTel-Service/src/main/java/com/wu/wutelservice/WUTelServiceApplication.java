package com.wu.wutelservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.wu.config.MsSqlServerConfig;
import com.wu.config.SwaggerConfiguration;

@SpringBootApplication
@ComponentScan(basePackages = "com.wu")
@Import({ SwaggerConfiguration.class,MsSqlServerConfig.class})

public class WUTelServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WUTelServiceApplication.class, args);
	}

}
