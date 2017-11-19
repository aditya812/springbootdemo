/*package com.example.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

	@Configuration
	@EnableTransactionManagement
	@ComponentScan({"com.example.dao"})
	public class HibernateConfigurations {
	    @Autowired
	    private Environment environment;
	    
	    
	    
	    @Bean
	    public LocalSessionFactoryBean sessionFactory() throws IllegalStateException, PropertyVetoException {
	        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource());
	        sessionFactory.setPackagesToScan(new String[] {"com.example.model" });
	        sessionFactory.setHibernateProperties(hibernateProperties());
	        return sessionFactory;
	    }

	    @Bean
	    public DataSource dataSource() throws IllegalStateException, PropertyVetoException {
	    	ComboPooledDataSource cpds= new ComboPooledDataSource();
	    	cpds.setDriverClass(environment.getRequiredProperty("jdbc.driverClassName"));
	    	cpds.setJdbcUrl(environment.getRequiredProperty("jdbc.url"));
	    	cpds.setUser(environment.getRequiredProperty("jdbc.username"));
	    	cpds.setPassword(environment.getRequiredProperty("jdbc.password"));    
	    	cpds.setMinPoolSize(Integer.parseInt(environment.getRequiredProperty("hibernate.c3p0.min_size")));
	    	cpds.setMaxPoolSize(Integer.parseInt(environment.getRequiredProperty("hibernate.c3p0.max_size")));
	    	cpds.setAcquireIncrement(Integer.parseInt(environment.getRequiredProperty("hibernate.c3p0.acquire_increment")));
	    	cpds.setIdleConnectionTestPeriod(Integer.parseInt(environment.getRequiredProperty("hibernate.c3p0.idle_test_period")));
	    	cpds.setMaxStatements(Integer.parseInt(environment.getRequiredProperty("hibernate.c3p0.max_statements")));
	    	cpds.setMaxStatementsPerConnection(Integer.parseInt(environment.getRequiredProperty("hibernate.c3p0.max_Statements_Per_Connection")));
	    	cpds.setCheckoutTimeout(Integer.parseInt(environment.getRequiredProperty("hibernate.c3p0.timeout"))); 
	    	cpds.setStatementCacheNumDeferredCloseThreads(1);
	    	
	        return cpds;
	    }

	    private Properties hibernateProperties() {
	        Properties properties = new Properties();
	        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
	        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
	        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
	        properties.put("connection.provider_class", environment.getRequiredProperty("connection.provider_class"));
	        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
	        return properties;
	    }

	    @Bean
	    @Autowired
	    public HibernateTransactionManager transactionManager(SessionFactory s) {
	        HibernateTransactionManager txManager = new HibernateTransactionManager(s);
	        return txManager;
	    }
}
*/