package model;

import java.util.ArrayList;
import java.util.List;

public class ControllerModel {
List<Employee> employees;
List<Ingredient> ingredients;

	public ControllerModel() {
		
		employees= new ArrayList<Employee>();
		ingredients =new ArrayList<Ingredient>();
	}
	
	public List<Employee> getEmployees(){
		return employees;
	}
	
	public List<Ingredient> getIngredients(){
		return ingredients;
	}
	
	public boolean addEmployee(Employee newEmployee) {
		
		if(employees.add(newEmployee)) {
			return true;
		}
		else
			return false;
	}
	
	public boolean addIngredient(Ingredient newIngredient) {
		if(ingredients.add(newIngredient)) {
			return true;
		}
		else return false;
	}
}
