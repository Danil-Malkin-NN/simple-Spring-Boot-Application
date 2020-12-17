package com.example.demo.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("default.properties")
@PropertySource(value = "local.properties", ignoreResourceNotFound = true)
public class ServiceConf {
}
