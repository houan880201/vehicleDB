package howard.vehicle.manager.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import howard.vehicle.manager.dao.VehicleDAO;
import howard.vehicle.manager.dao.VehicleDAOImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "howard.vehicle.manager")
public class SpringMvcConfig implements WebMvcConfigurer{
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://vehicledb.cpqskxkvq3rw.us-west-2.rds.amazonaws.com/innodb");

		//dataSource.setUrl("jdbc:mysql://ns3059021:3306/vehicledb?autoReconnect=true&useSSL=false");
		dataSource.setUsername("admin");
		dataSource.setPassword("password");
		return dataSource;
	}
	
	@Bean 
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public VehicleDAO getVehicleDAO() {
		return new VehicleDAOImpl(getDataSource());
	}
	

}
