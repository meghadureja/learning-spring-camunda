package edu.practice.workflow.config;

import org.springframework.beans.factory.annotation.Qualifier;
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

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
  entityManagerFactoryRef = "primaryEntityManagerFactory",
  transactionManagerRef = "primaryTransactionManager",
  basePackages = {"edu.practice.workflow.repository"}
)
public class DataSourceConfig {

  @Primary
  @Bean(name = "primaryDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.db1")
  public DataSource primaryDataSource(){
    return DataSourceBuilder.create().build();
  }

  @Primary
  @Bean(name = "primaryEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("primaryDataSource") DataSource dataSource){
    return builder
      .dataSource(dataSource)
      .packages("edu.practice.workflow.entity")
      .persistenceUnit("primary")
      .build();
  }

  @Primary
  @Bean(name = "primaryTransactionManager")
  public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryEntityManagerFactory") EntityManagerFactory factory){
    return new JpaTransactionManager(factory);
  }

}
