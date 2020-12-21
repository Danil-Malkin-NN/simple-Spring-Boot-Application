package com.example.demo.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ 
    AppPackages.SERVICES, 
    AppPackages.JPA, 
    AppPackages.CONTROLLERS, 
    AppPackages.MODEL })
public class RootConfiguration {
}