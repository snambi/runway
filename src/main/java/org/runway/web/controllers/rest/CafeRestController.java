package org.runway.web.controllers.rest;

import java.util.List;

import org.runway.employees.domain.Employee;
import org.runway.employees.service.IEmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( { "/r/cafes" })
public class CafeRestController {
	
	@Autowired
	private IEmployeeService cafeService;
	
	@RequestMapping(value="/id/{cafeId}", method = RequestMethod.GET)
	public @ResponseBody Employee getCafeById(@PathVariable Integer cafeId) {
		return cafeService.get(cafeId.intValue());
	}

	@RequestMapping(value="/name/{cafeName}", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getCafeByName(@PathVariable String cafeName) {
		return cafeService.getByName(cafeName);
	}
	

	@RequestMapping(value="/all", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllCafes(){
		return cafeService.getAllEmployees();
	}
	

	@RequestMapping(value="/allNames", method = RequestMethod.GET)
	public @ResponseBody List<String> getAllCafeNames(){
		return cafeService.getAllEmployeeNames();
	}
	
}
