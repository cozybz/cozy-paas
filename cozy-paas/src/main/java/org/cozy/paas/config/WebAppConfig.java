package org.cozy.paas.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("org.cozy.paas.service.impl")
@MapperScan("org.cozy.paas.dao")
@PropertySource("classpath:/org/cozy/paas/config/jdbc.properties")
@EnableTransactionManagement
public class WebAppConfig {
	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		PoolProperties p = new PoolProperties();
		p.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		p.setUrl(env.getProperty("jdbc.url"));
		p.setName(env.getProperty("jdbc.username"));
		p.setPassword(env.getProperty("jdbc.password"));
		return new DataSource(p);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		return factoryBean.getObject();
	}
	
	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}

}
