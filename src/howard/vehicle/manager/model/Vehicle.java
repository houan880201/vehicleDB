package howard.vehicle.manager.model;

public class Vehicle {

	public int id;
	public int year;
	public String make;
	public String model;

	public Vehicle() {
		
	}
	
	
	public Vehicle(int year, String make, String model) {
		this.year = year;
		this.make = make;
		this.model = model;
	}
	public Vehicle(int id, int year, String make, String model) {
		this.id = id;
		this.year = year;
		this.make = make;
		this.model = model;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", year=" + year + ", make=" + make + ", model=" + model + "]";
	}

}
