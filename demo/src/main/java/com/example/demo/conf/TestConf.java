package com.example.demo.conf;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@Profile("test")
@EnableJpaRepositories(basePackages = AppPackages.JPA)
@EntityScan(basePackages = AppPackages.MODEL)
@PropertySource("classpath:/test.default.properties")
@PropertySource(value = "classpath:/test.local.properties", ignoreResourceNotFound = true)
public class TestConf {

}
