package howard.vehicle.manager.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import howard.vehicle.manager.model.Vehicle;

class VehicleDAOTest {
	
	private DriverManagerDataSource dataSource;
	private VehicleDAO dao;
	private JdbcTemplate jdbcTemplate;


	
	@BeforeEach
	void setUp() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://vehicledb.cpqskxkvq3rw.us-west-2.rds.amazonaws.com/innodb?autoReconnect=true&useSSL=false");
		dataSource.setUsername("admin");
		dataSource.setPassword("password");
		dao = new VehicleDAOImpl(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		safeClear();
	}
	
	/* This is a helper method to populate database before each test */
	void populateDB() {
		String sql = "INSERT INTO vehicle (Year, Make, Model) VALUES (?,?,?)";
		jdbcTemplate.update(sql, 2020, "Toyota", "1st");
		jdbcTemplate.update(sql, 2020, "Toyota", "2nd");
		jdbcTemplate.update(sql, 2020, "Toyota", "3rd");
	}
	
	/* Safely clear database entries, used for testing */
	void safeClear() {
		
		try {
			String sql = "SET SQL_SAFE_UPDATES = 0";
			jdbcTemplate.update(sql);
			sql = "DELETE FROM vehicle";
			jdbcTemplate.update(sql);
			sql = "SET SQL_SAFE_UPDATES = 1";
			jdbcTemplate.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* This a helper method to add one element to database */
	void addOneToDB() {
		String sql = "INSERT INTO vehicle (Id, Year, Make, Model) VALUES (?,?,?,?)";
		jdbcTemplate.update(sql, 1, 2020, "Toyota", "1st");
	}
	
	
	/* 
	 * TESTING GET VEHICLES 
	 * */
	
	
	// TEST : normal case, retrieving list of vehicles
	@Test 
	void testGetVehicles() {
		populateDB();
		List<Vehicle> vehicles = dao.getVehicles();
		assertFalse(vehicles.isEmpty());
		assertEquals(vehicles.size(), 3);
	}
	
	
	// TEST : Empty case
	@Test
	void testGetVehiclesEmpty() {
		List<Vehicle> vehicles = dao.getVehicles();
		assertTrue(vehicles.isEmpty());
		assertEquals(vehicles.size(), 0);
	}
	
	
	// TEST : Single Case
	@Test
	void testGetVehicleSingle() {
		addOneToDB();
		List<Vehicle> vehicles = dao.getVehicles();
		assertFalse(vehicles.isEmpty());
		assertEquals(vehicles.size(), 1);
	}
	
	
	/*
	 * TEST GET SINGLE VEHICLE WITH ID
	 */
	
	
	// GET Existing ID
	@Test
	void testGet() {
		addOneToDB();
		Integer id = 1;
		Vehicle vehicle = dao.get(id);
		assertNotNull(vehicle);
		assertEquals(vehicle.getId(), id);
	}
	
	
	// GET non existing ID
	@Test
	void testGetNotExist() {
		Integer id = 1000000;  //assuming the database hasn't been populated till this point
		Vehicle vehicle = dao.get(id);
		assertNull(vehicle);
	}
	
	
	// GET invalid ID
	@Test
	void testGetInvalidID() {
		Integer id = -1;
		Vehicle vehicle = dao.get(id);
		assertNull(vehicle);
		id = 0;
		vehicle = dao.get(id);
		assertNull(vehicle);
	}


	/*
	 *  TEST CREATE VEHICLES
	 */
	
	// CREATE successful / valid vehicle
	@Test
	void testSave() {
		Vehicle vehicle = new Vehicle(1900, "Honda", "ModelX");
		int result = dao.save(vehicle);
		assertTrue(result > 0);
	}
	
	
	// CREATE invalid vehicle
	@Test
	void testSaveNullFields() {
		Vehicle vehicle = new Vehicle();
		int result = dao.save(vehicle);
		assertTrue(result == 0);
	}
	
	
	/*
	 * TEST UPDATE VEHICLES
	 */

	
	// UPDATE existing vehicle
	@Test
	void testUpdate() {
		addOneToDB();
		Vehicle vehicle = new Vehicle(1, 2009, "Tesla", "NoModel");
		int result = dao.update(vehicle); //numOfRows changed
		assertTrue(result > 0);
		
		Vehicle updatedVehicle = dao.get(1);
		assertEquals(vehicle.getYear(), updatedVehicle.getYear());
		assertEquals(vehicle.getMake(), updatedVehicle.getMake());
		assertEquals(vehicle.getModel(), updatedVehicle.getModel());
	}
	
	
	// UPDATE non existing vehicle
	@Test
	void testUpdateNotExists () {
		Vehicle vehicle = new Vehicle(1000000, 2009, "Tesla", "NoModel");
		int result = dao.update(vehicle); //numOfRows changed
		assertTrue(result == 0);
	}
	
	
	
	/*
	 * TEST DELETE VEHICLES
	 */
	
	
	// DELETE existing ID
	@Test
	void testDelete() {
		addOneToDB();
		Integer id = 1;
		int result = dao.delete(id);
		assertTrue(result > 0);
	}
	
	
	// DELETE non existing ID
	@Test
	void testDeleteNotExists () {
		Integer id = 100000000;
		int result = dao.delete(id);
		assertTrue(result == 0);
	}
	
	
	// DELETE invalid ID 
	@Test
	void testDeleteInvalidID () {
		Integer id = -1;
		int result = dao.delete(id);
		assertTrue(result == 0);
		id = 0;
		result = dao.delete(id);
		assertTrue(result == 0);
	}

}
