package howard.vehicle.manager.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import howard.vehicle.manager.model.Vehicle;

class VehicleDAOTest {
	
	private DriverManagerDataSource dataSource;
	private VehicleDAO dao;
	
	@BeforeEach
	void setUp() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/vehicledb?autoReconnect=true&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("password");
		dao = new VehicleDAOImpl(dataSource);
	}

	@Test
	void testSave() {
		Vehicle vehicle = new Vehicle(1900, "Honda", "ModelX");
		int result = dao.save(vehicle);
		assertTrue(result > 0);
	}


	@Test
	void testUpdate() {
		Vehicle vehicle = new Vehicle(5, 2009, "Tesla", "NoModel");
		int result = dao.update(vehicle);
		assertTrue(result > 0);
	}
	
	
	@Test
	void testGet() {
		Integer id = 1;
		Vehicle vehicle = dao.get(id);
		if (vehicle !=  null) {
			System.out.println(vehicle);
		}
		assertNotNull(vehicle);
	}


//	@Test
//	void testDelete() {
//		Integer id = 13;
//		int result = dao.delete(id);
//		assertTrue(result > 0);
//	}

	@Test
	void testGetVehicles() {

		List<Vehicle> vehicles = dao.getVehicles();
		
		for(Vehicle v : vehicles) {
			System.out.println(v);
		}
		
		assertFalse(vehicles.isEmpty());
	
	}

}
