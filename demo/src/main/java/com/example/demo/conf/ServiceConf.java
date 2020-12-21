package com.example.demo.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/default.properties")
@PropertySource(value = "classpath:/local.properties", ignoreResourceNotFound = true)
public class ServiceConf {
}
