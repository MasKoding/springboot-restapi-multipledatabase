package com.example.demo2.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        basePackages = {"com.example.demo2.mysql.dao"},
        transactionManagerRef = "mysqlTransactionManager"
)
public class MysqlJpaConfiguration {

    @Primary
    @Bean("mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Primary
    @Bean(name="mysqlEntityManagerFactory")
   public  LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder,@Qualifier("mysqlDataSource") DataSource dataSource)
    {
        return entityManagerFactoryBuilder.dataSource(dataSource)
                .packages("com.example.demo2.mysqlentity")
                .persistenceUnit("mysqlPU")
                .properties(hibernateProperties("spring.jpa.mysql"))
                .build();
    }

    @Primary
    @Bean(name = "mysqlTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(
            @Qualifier("mysqlEntityManagerFactory")EntityManagerFactory entityManagerFactory
            ){
        return new JpaTransactionManager(entityManagerFactory);
    }
    public Map<String,Object> hibernateProperties(String prefix){
        return Map.of(
                "hibernate.dialect","org.hibernate.dialect.MySQLDialect",
                "hibernate.hbm2ddl.auto","update"
        );
    }
}
