package com.parkcontrol.backend.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class NeonDataSourceConfig {

    @Value("${NEON_DATASOURCE_URL:jdbc:postgresql://ep-solitary-bird-aceiuk5i-pooler.sa-east-1.aws.neon.tech/neondb?sslmode=require}")
    private String url;

    @Value("${NEON_DATASOURCE_USERNAME:neondb_owner}")
    private String username;

    @Value("${NEON_DATASOURCE_PASSWORD:npg_jWQhv5EM1orI}")
    private String password;

    @Bean
    public DataSource neonDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("org.postgresql.Driver");
        config.setMaximumPoolSize(5);
        config.setConnectionTimeout(10000);
        return new HikariDataSource(config);
    }

    @Bean
    public JdbcTemplate neonJdbcTemplate(DataSource neonDataSource) {
        return new JdbcTemplate(neonDataSource);
    }
}