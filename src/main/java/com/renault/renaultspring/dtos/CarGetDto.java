package com.renault.renaultspring.dtos;

public class CarGetDto {
	private String brand;
	
	public CarGetDto() {}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "CarGetDto [brand=" + brand + "]";
	}

	
	
}
