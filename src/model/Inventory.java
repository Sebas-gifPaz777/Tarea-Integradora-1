package model;

public class Inventory {
	private String ingredientName, unit;

	public Inventory(String ingredientName, String unit) {
		this.ingredientName=ingredientName;
		this.unit=unit;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
