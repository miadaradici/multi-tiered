package com.eby.bestapp.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.eby.bestapp.dao.UserDAO;
import com.eby.bestapp.model.Capacity;
import com.eby.bestapp.model.Ceremony;
import com.eby.bestapp.model.Comentariu;
import com.eby.bestapp.model.Project;
import com.eby.bestapp.model.Sprint;
import com.eby.bestapp.model.User;

@Configuration
@EnableWebMvc
@ComponentScan (basePackages = "com.eby.bestapp")
@EnableTransactionManagement
public class AppConfig {
	
	@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource =
				new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages");
		return messageSource;
	}
	
	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = 
				new BasicDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/multitired");
		dataSource.setUsername("postgres");
		dataSource.setPassword("admin");
		return dataSource;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Ceremony.class);
		sessionBuilder.addAnnotatedClass(Sprint.class);
		sessionBuilder.addAnnotatedClass(Comentariu.class);
		sessionBuilder.addAnnotatedClass(Capacity.class);
		sessionBuilder.addAnnotatedClass(Project.class);
		sessionBuilder.addAnnotatedClass(UserDAO.class);
		sessionBuilder.addProperties(getHibernateProperties());
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
	
//	@Autowired
//	@Bean(name = "userDAO")
//	public UserDAO getUserDao(
//			SessionFactory sessionFactory) {
//		return new UserDAO(sessionFactory);
//	}
	
	private Properties getHibernateProperties() {
	    Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "false");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
	    properties.put("hibernate.format_sql", true);
	    return properties;
	}

	@Bean
	public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
		PropertyPlaceholderConfigurer propertyPlaceholderConfigurer =
							new PropertyPlaceholderConfigurer();
		Properties properties = new Properties();
		properties.put("location", "WEB-INF/resources/messages.properties");
		propertyPlaceholderConfigurer.setProperties(properties);
		
		return propertyPlaceholderConfigurer;
	}
	
//	@Bean
//	public TilesConfigurer tilesConfigurer() {
//		TilesConfigurer tiles = new TilesConfigurer();
//		tiles.setDefinitions(new String[] {
//				"WEB-INF/layout/tiles.xml"
//		});
//		tiles.setCheckRefresh(true);
//		
//		return tiles;
//	}
//	
//	@Bean
//	public ViewResolver viewResolver() {
//		return new TilesViewResolver();
//	}

}
