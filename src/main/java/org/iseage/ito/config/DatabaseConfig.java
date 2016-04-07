package org.iseage.ito.config;

import liquibase.integration.spring.SpringLiquibase;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class DatabaseConfig {

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    public BasicDataSource mySQLDataSource() {
        BasicDataSource mySQLDataSource = new BasicDataSource();
        mySQLDataSource.setUrl(this.url);
        mySQLDataSource.setDriverClassName(this.driver);
        mySQLDataSource.setUsername(this.username);
        mySQLDataSource.setPassword(this.password);
        mySQLDataSource.setRemoveAbandoned(true);
        mySQLDataSource.setInitialSize(5);
        mySQLDataSource.setTestOnBorrow(true);
        mySQLDataSource.setValidationQuery("SELECT 1");
        mySQLDataSource.setMaxWait(10000);
        mySQLDataSource.setRemoveAbandonedTimeout(60);
        mySQLDataSource.setMaxActive(10);
        return mySQLDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(mySQLDataSource());
        return jdbcTemplate;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(mySQLDataSource());
        liquibase.setChangeLog("classpath:liquibase/changelog.xml");
        return liquibase;
    }

}
