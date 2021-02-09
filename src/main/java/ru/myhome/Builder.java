package ru.myhome;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;


@Configuration
@ComponentScan
@PropertySource("classpath:jdbc.properties")
public class Builder {

	@Autowired
    Environment env;
	
	@Bean
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
}
