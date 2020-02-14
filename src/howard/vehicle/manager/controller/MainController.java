package howard.vehicle.manager.controller;

/*
 * This file contains routes that the Web Client uses.
 * The routes include CRUD operations where the methods
 * in vehicleDAO are called.
 */

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import howard.vehicle.manager.dao.VehicleDAO;
import howard.vehicle.manager.model.Vehicle;

@Controller
public class MainController {

	@Autowired
	private VehicleDAO vehicleDAO;
	
	
	/*
	 * The index page / root page.
	 */
	@RequestMapping(value = "/")
	public ModelAndView listVehicle(ModelAndView model) {
		List<Vehicle> listVehicle = vehicleDAO.getVehicles();
		model.addObject("listVehicle", listVehicle);
		model.setViewName("index");
		return model;
	}
	
	
	/*
	 * Route for rendering the form to create / update vehicle
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newVehicle(ModelAndView model) {
		
		Vehicle vehicle = new Vehicle();
		model.addObject("vehicle", vehicle);
		model.setViewName("vehicle_form");
		return model;
	}
	
	
	/*
	 * Route for saving. This includes creating or updating 
	 * of the new vehicle to the database.
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveVehicle(@ModelAttribute Vehicle vehicle) {
		if(vehicle.getId() == 0) {
			vehicleDAO.save(vehicle);
		} else {
			vehicleDAO.update(vehicle);
		}
		return new ModelAndView("redirect:/");
	}
	
	
	/*
	 * This is the route that renders the create / update form
	 * with the ID. This is used to make updates to the vehicle.
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editVehicle(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Vehicle vehicle = vehicleDAO.get(id);
		ModelAndView model = new ModelAndView();
		
		model.addObject("vehicle", vehicle);
		model.setViewName("vehicle_form");
		return model;
	}
	
	
	/*
	 * This is used to delete the vehicle associated to the ID.
	 * Redirect to home page after operation is completed.
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteVehicle(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		vehicleDAO.delete(id);
		return new ModelAndView("redirect:/");
	}
	
}


