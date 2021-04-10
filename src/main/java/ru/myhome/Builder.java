package ru.myhome;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@PropertySource("classpath:jdbc.properties")
@PropertySource("classpath:hibernate.properties")
@ComponentScan
public class Builder {

	@Autowired
    Environment env;
	
	@Bean(destroyMethod = "close")
	public BasicDataSource getDataSource() {
		BasicDataSource bds = new BasicDataSource();
		bds.setUrl(env.getProperty("jdbc.url"));
		bds.setUsername(env.getProperty("jdbc.username"));
		bds.setPassword(env.getProperty("jdbc.password"));
		return bds;
	}
	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		Map<String, Object> prop = new HashMap<String, Object>();
		/*prop.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		prop.put("hibernate.max_fetch_depth", env.getProperty("hibernate.max_fetch_depth"));
		prop.put("hibernate.max_fetch_size", env.getProperty("hibernate.max_fetch_size"));
		prop.put("hibernate.max_batch_size", env.getProperty("hibernate.max_batch_size"));
		prop.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));*/
		prop.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
		prop.put("hibernate.max_fetch_depth",3);
		prop.put("hibernate.max_fetch_size",50);
		prop.put("hibernate.max_batch_size",10);
		prop.put("hibernate.show_sql",true);
		
		LocalContainerEntityManagerFactoryBean entityManager = 
				new LocalContainerEntityManagerFactoryBean();
		entityManager.setDataSource(getDataSource());
		entityManager.setJpaVendorAdapter(
				new HibernateJpaVendorAdapter());
		entityManager.setJpaPropertyMap(prop);
		entityManager.setPackagesToScan("ru.myhome");
		return entityManager;
	}
	
	@Bean
	@Lazy
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
