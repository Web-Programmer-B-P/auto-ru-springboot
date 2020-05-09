package ru.petr.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
public class JdbcConfig {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${connection.pool.initialPoolSize}")
    private String initialPoolSize;

    @Value("${connection.pool.minPoolSize}")
    private String minPoolSize;

    @Value("${connection.pool.maxPoolSize}")
    private String maxPoolSize;

    @Value("${connection.pool.maxIdleTime}")
    private String maxIdleTime;

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() throws PropertyVetoException {
        return new NamedParameterJdbcTemplate(securityDataSource());
    }

    @Bean
    public DataSource securityDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setInitialPoolSize(parseString(initialPoolSize));
        dataSource.setMinPoolSize(parseString(minPoolSize));
        dataSource.setMaxPoolSize(parseString(maxPoolSize));
        dataSource.setMaxIdleTime(parseString(maxIdleTime));
        return dataSource;
    }

    private int parseString(String property) {
        return Integer.parseInt(property);
    }
}

