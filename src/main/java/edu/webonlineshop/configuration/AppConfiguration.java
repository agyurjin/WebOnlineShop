package edu.webonlineshop.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan("edu.webonlineshop")
@ImportResource("classpath:applicationContext.xml")
public class AppConfiguration {
}