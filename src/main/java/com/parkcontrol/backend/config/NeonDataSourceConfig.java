package com.parkcontrol.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class NeonDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "neon.datasource")
    public DataSource neonDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate neonJdbcTemplate(DataSource neonDataSource) {
        return new JdbcTemplate(neonDataSource);
    }
}