package com.easyoops.common.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {"com.**.**.repository"})
@EnableTransactionManagement
public class DataSourceConfig {

	private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);

	@Primary
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariDataSource dataSource(){
		return DataSourceBuilder
				.create()
				.type(HikariDataSource.class)
				.build();
	}

	//	@Bean(name = "mysqlDb")
	//	@ConfigurationProperties(prefix="spring.datasource")
	//	public DataSource dataSource2(){
	//		return DataSourceBuilder
	//				.create()
	//				.build();
	//	}

	@Primary
	@Bean(name = "jpaProperties")
	@ConfigurationProperties(prefix = "spring.jpa")
	public JpaProperties jpaProperties() {
		return new JpaProperties();
	}

	@Primary
	@Bean( name = "entityManagerFactory" )
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("dataSource") DataSource dataSource,
																	   @Qualifier("jpaProperties") JpaProperties jpaProperties) {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setPersistenceUnitName("default");
		entityManagerFactory.setPackagesToScan("com.**.**.entity");
		entityManagerFactory.setJpaPropertyMap(jpaProperties.getProperties());
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return entityManagerFactory;
//		return entityManagerFactoryBuilder
//				.dataSource(dataSource)
//				.properties(jpaProperties.getProperties())
//				.packages("com.**.**.entity")
//				.persistenceUnit("default")
//				.build();
	}

	@Primary
	@Bean(name = "transactionManager")
	@DependsOn({"entityManagerFactory"})
	PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
//		return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory().getObject()));
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactory.getObject());
//		jpaTransactionManager.setNestedTransactionAllowed(true);
		return jpaTransactionManager;
	}
}
