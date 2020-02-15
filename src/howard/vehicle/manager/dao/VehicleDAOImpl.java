package howard.vehicle.manager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import howard.vehicle.manager.model.Vehicle;

public class VehicleDAOImpl implements VehicleDAO {

	private JdbcTemplate jdbcTemplate;
	
	public VehicleDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	/* Insert a vehicle into database entry*/
	@Override
	public int save(Vehicle vehicle) {

		String sql = "INSERT INTO vehicle (Year, Make, Model) VALUES (?,?,?)";
		
		int res = 0;
		try {
			res = jdbcTemplate.update(sql, vehicle.getYear(), vehicle.getMake(), vehicle.getModel());	
			return res;
		} catch (Exception e) {
			return 0;
		}
	}

	
	
	/* Get a specific vehicle entry from database given ID */
	@Override
	public Vehicle get(Integer id) {

		String sql = "SELECT * FROM vehicle WHERE Id=" + id;
		ResultSetExtractor<Vehicle> extractor = new ResultSetExtractor<Vehicle>() {

			@Override
			public Vehicle extractData(ResultSet rs) throws SQLException, DataAccessException {

				if(rs.next()) {
					int id = rs.getInt("Id");
					int year = rs.getInt("Year");
					String make = rs.getString("Make");
					String model = rs.getString("Model");
					
					return new Vehicle(id, year, make, model);
				}
				
				return null;
			}
			
		};
		return jdbcTemplate.query(sql, extractor) ;
	}

	
	
	/* Update vehicle given an vehicle Object */
	@Override
	public int update(Vehicle vehicle) {

		String sql = "UPDATE vehicle SET Year=?, Make=?, Model=? WHERE Id=?";
		return jdbcTemplate.update(sql, vehicle.getYear(), 
				vehicle.getMake(), vehicle.getModel(), vehicle.getId());	
	}

	
	/* Delete specific vehicle given ID */
	@Override
	public int delete(Integer id) {

		String sql = "DELETE FROM vehicle WHERE Id=" + id;
		return jdbcTemplate.update(sql);
	}

	
	/* Return all lists of vehicles from database */
	@Override
	public List<Vehicle> getVehicles() {

		String sql = "SELECT * FROM vehicle";
		RowMapper<Vehicle> vehicleMapper = new RowMapper<Vehicle>() {

			@Override
			public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
				int id = rs.getInt("Id");
				int year = rs.getInt("Year");
				String make = rs.getString("Make");
				String model = rs.getString("Model");
				
				return new Vehicle(id, year, make, model);
			}
			
		};
		return jdbcTemplate.query(sql, vehicleMapper);
	}
	

}
