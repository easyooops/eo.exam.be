package com.easyoops.common.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Objects;

@Configuration
@MapperScan(basePackages = "com.easyoops.**.mapper")
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
		transactionManagerRef = "transactionManager",
		basePackages = {"com.easyoops.**.repository"})
@EnableTransactionManagement
public class DataSourceConfig {

	private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);

//	@PersistenceUnit
//	EntityManagerFactory entityManagerFactory;

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

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

		sessionFactory.setDataSource(dataSource());
		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:sql/**/*.xml"));
		sessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		return sessionFactory.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}


	@Primary
	@Bean( name = "entityManagerFactory" )
//	@ConfigurationProperties(prefix="spring.jpa")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan("com.easyoops.**.entity");
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return entityManagerFactory;
	}

	@Primary
	@Bean(name = "transactionManager")
	@DependsOn({"entityManagerFactory"})
	PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory().getObject()));
	}

//	@Bean
//	public JpaTransactionManager transactionManager() {
//		return new JpaTransactionManager(entityManagerFactory);
//	}
//	@Bean
//	public DataSourceTransactionManager transactionManager(){
//		DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource());
//		return manager;
//	}

}
