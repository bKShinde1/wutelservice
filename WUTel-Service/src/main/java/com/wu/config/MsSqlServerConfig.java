package com.wu.config;

import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "msSqlServerEntityManagerFactory", transactionManagerRef = "msSqlServerTransactionManager", basePackages = {
		"com.wu.models", "com.wu.dao", "com.wu.dto" })
public class MsSqlServerConfig {

	private static final Logger LOG = LoggerFactory.getLogger(MsSqlServerConfig.class);

	@Value("${spring.datasource.jndi-name}")
	private String msSqlServerJndiName;

	@Bean(name = "msSqlServerDataSource", destroyMethod = "")
	@ConfigurationProperties("spring.datasource")
	public DataSource msSqlServerDataSource() {
		DataSource dataSource = null;
		try {
			JndiTemplate jndi = new JndiTemplate();
			dataSource = jndi.lookup(msSqlServerJndiName, DataSource.class);
		} catch (NamingException ex) { 
			LOG.error("NamingException for MsSqlServerConfig: {}", msSqlServerJndiName, ex);
		}
		
		return dataSource;
	}

	@Bean(name = "msSqlServerEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("msSqlServerDataSource") DataSource dataSource) {
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.physical_naming_strategy",
				"org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
		return builder.dataSource(dataSource).packages("com.wu.models", "com.wu.dao", "com.wu.dto")
				.persistenceUnit("msSqlServerUnit").properties(properties).build();
	}

	@Bean(name = "msSqlServerEntityManager")
	public EntityManager buildEntityManager(EntityManagerFactoryBuilder builder,
			@Qualifier("msSqlServerEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return entityManagerFactory.createEntityManager();
	}

	@Bean(name = "msSqlServerTransactionManager")
	public PlatformTransactionManager msSqlServerTransactionManager(
			@Qualifier("msSqlServerEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
