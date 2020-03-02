package com.renault.renaultspring.services.car;

import java.util.List;

import com.renault.renaultspring.dtos.CarDto;

public interface CarService {
	List<CarDto> get(String brand);
}
