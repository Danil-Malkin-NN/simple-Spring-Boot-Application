package com.example.demo;

import com.example.demo.conf.RootConfiguration;
import com.example.demo.conf.TestConf;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = { TestConf.class, 
        RootConfiguration.class })
public class TestBase {
}