package com.renault.renaultspring.dtos;

public class CarDeleteDto {
	
	private String column;
	private String value;
	
	public CarDeleteDto() {
		// TODO Auto-generated constructor stub
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "CarDeleteDto [column=" + column + ", value=" + value + "]";
	}
	
	
}
