package com.easyoops.common.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Objects;

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
	@Bean( name = "entityManagerFactory" )
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan("com.**.**.entity");
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return entityManagerFactory;
	}

	@Primary
	@Bean(name = "transactionManager")
	@DependsOn({"entityManagerFactory"})
	PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory().getObject()));
	}
}
