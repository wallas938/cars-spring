package com.renault.renaultspring.dtos;

public class CarDto {
	
	private String brand;
	
	private String model;
	
	public CarDto() {}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	@Override
	public String toString() {
		return "CarDto [brand=" + brand + ", model=" + model + "]";
	}
	
	
}
