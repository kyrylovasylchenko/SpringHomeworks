package com.example.springhomeworks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
@Configuration
public class DataBaseConfig {

    @Bean
    public DataSource create(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver ");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/glovo");
        dataSource.setUsername("postgres");
        dataSource.setPassword("root");
        return dataSource;
    }
}
