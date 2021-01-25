package edu.practice.workflow.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
//@ConditionalOnProperty(name = "camunda.bpm.enabled", havingValue = "true", matchIfMissing = true)
public class CamundaDataSourceConfig {

  @Bean(name = "camundaBpmDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.db2")
  public DataSource secondaryDataSource(){
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "camundaBpmTransactionManager")
  public PlatformTransactionManager secondaryTransactionManager(@Qualifier("camundaBpmDataSource") DataSource dataSource){
    return new DataSourceTransactionManager(dataSource);
  }

}
