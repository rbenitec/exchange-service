package com.mibanco.rbenitez.mbexchangeservice.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
public class ServiceConfiguration {
    @Bean
    public ConnectionFactoryInitializer initialize(ConnectionFactory factory) {
        var initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(factory);
        var populator = new CompositeDatabasePopulator();
        populator.addPopulators(
                new ResourceDatabasePopulator(new ClassPathResource("/schema.sql"))
                //new ResourceDatabasePopulator(new ClassPathResource("/data.sql"))
        );
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}
