package com.example.demo2.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "db2EntityManagerFactory",
        basePackages = {"com.example.demo2.db2.dao"},
        transactionManagerRef = "db2TransactionManager"
)
public class Db2JpaConfiguration {

    @Bean(name = "db2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource db2DataSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "db2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db2EntityManagerFactory(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("db2DataSource") DataSource dataSource
            ){
        return entityManagerFactoryBuilder.dataSource(dataSource)
                .packages("com.example.demo2.db2entity")
                .persistenceUnit("db2PU")
                .properties(hibernateProperties())
                .build();
    }


    @Bean
    public PlatformTransactionManager db2TransactionManager(
           @Qualifier("db2EntityManagerFactory") EntityManagerFactory entityManagerFactory
    ){
        return new JpaTransactionManager(entityManagerFactory);
    }

    public Map<String,Object> hibernateProperties(){
        return Map.of(
                "hibernate.dialect","org.hibernate.dialect.DB2Dialect",
                "hibernate.hbm2ddl.auto","update"
        );
    }

}
