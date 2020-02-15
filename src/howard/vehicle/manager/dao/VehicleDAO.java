package howard.vehicle.manager.dao;

import java.util.List;

import howard.vehicle.manager.model.Vehicle;

public interface VehicleDAO {
	
	// CRUD Operations
	public int save(Vehicle vehicle);
	public Vehicle get(Integer id);
	public int update(Vehicle vehicle);
	public int delete(Integer id);
	public List<Vehicle> getVehicles();
}
