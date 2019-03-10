package com.molokotech.model;

public class Cat extends Animal {

	private float quantityFood;
	private String foodBrand;
	private String coockedFood;

	public float getQuantityFood() {
		return quantityFood;
	}

	public void setQuantityFood(float quantityFood) {
		this.quantityFood = quantityFood;
	}

	public String getFoodBrand() {
		return foodBrand;
	}

	public void setFoodBrand(String foodBrand) {
		this.foodBrand = foodBrand;
	}

	public String getCoockedFood() {
		return coockedFood;
	}

	public void setCoockedFood(String coockedFood) {
		this.coockedFood = coockedFood;
	}

}
