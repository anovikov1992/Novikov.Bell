package ru.bellintegrator.practice;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import ru.bellintegrator.practice.Application;

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(basePackages = "ru.bellintegrator.practice",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Application.class)})
public class TestConfig {}
