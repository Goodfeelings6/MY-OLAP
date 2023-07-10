package com.example.backend.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {
//
//    @Bean(name = "dataSource")
//    @Qualifier("dataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public HikariDataSource dataSource(){
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
}