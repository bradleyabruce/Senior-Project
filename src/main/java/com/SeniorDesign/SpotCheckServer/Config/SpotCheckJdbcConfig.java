package com.SeniorDesign.SpotCheckServer.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Configuration
@ComponentScan
public class SpotCheckJdbcConfig {
    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://174.101.154.93;databaseName=SpotCheckDB");
        dataSource.setUsername("spotCheckAdmin");
        dataSource.setPassword("Clermont16");

        return dataSource;
    }
}
