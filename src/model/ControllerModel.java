package model;

import java.util.ArrayList;
import java.util.List;

public class ControllerModel {
List<Employee> employees;
	
	public ControllerModel() {
		
		employees= new ArrayList<Employee>();
	}
	
	public List<Employee> getEmployees(){
		return employees;
	}
	
	public boolean addEmployee(Employee newEmployee) {
		
		if(employees.add(newEmployee)) {
			return true;
		}
		else
			return false;
	}
}
