package howard.vehicle.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import howard.vehicle.manager.dao.VehicleDAO;
import howard.vehicle.manager.model.Vehicle;

@Controller
public class MainController {

	@Autowired
	private VehicleDAO vehicleDAO;
	
	@RequestMapping(value = "/")
	public ModelAndView listVehicle(ModelAndView model) {
		
		List<Vehicle> listVehicle = vehicleDAO.getVehicles();
		model.addObject("listVehicle", listVehicle);
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value = "/newVehicle", method = RequestMethod.GET)
	public ModelAndView newVehicle(ModelAndView model) {
		
		Vehicle vehicle = new Vehicle();
		model.addObject("vehicle", vehicle);
		model.setViewName("vehicle_form");
		return model;
	}
	
}


