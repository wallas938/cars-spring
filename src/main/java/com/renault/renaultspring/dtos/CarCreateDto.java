package com.renault.renaultspring.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CarCreateDto {
	
	@NotBlank
	@Size(min = 1, max = 30)
	private String brand;
	
	@NotBlank
	@Size(min = 1, max = 30)
	private String model;
	
	public CarCreateDto() {}

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
		return "CarCreateDto [brand=" + brand + ", model=" + model + "]";
	}
	
	
}
