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
		
		if(employees.add(newEmployee) && newEmployee!=null) {
			return true;
		}
		else
			return false;
	}
	
	public int checkUsers(String idc, String passw) {
		int ok=999999;
		
		for(int i=0;i<getEmployees().size();i++) {
			if(getEmployees().get(i).getId().equals(idc)) {
				if(getEmployees().get(i).getPassword().equals(passw)) {
					ok=i;
				}
			}
		}
		
		return ok;
	}
	
	public boolean confirmNewUser(String id) {
		
		boolean sn=false;
		for(int i=0;i<getEmployees().size();i++) {
			if(getEmployees().get(i).getId().equals(id)) {
				sn=true;
			}
		}
		
		return sn;
	}
}
