package com.renault.renaultspring.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.renault.renaultspring.dtos.CarCreateDto;
import com.renault.renaultspring.dtos.CarDeleteDto;
import com.renault.renaultspring.dtos.CarDto;
import com.renault.renaultspring.services.car.CarServiceImpl;

@RestController
@ResponseBody
@CrossOrigin(origins = "*")
public class CarController {
	
	private final CarServiceImpl service;
	
	public CarController(CarServiceImpl service) {
		this.service = service;
	}
	
	@GetMapping(value = "cars")
	public List<CarDto> get(@RequestParam(name = "brand", required = true) String brand) {
		return service.get(brand);
	}	
	
	@PostMapping(value = "cars/insert")
	public String insert(@RequestBody(required = true) CarCreateDto dto) {
		return service.post(dto);
	}
	
	@DeleteMapping(value = "cars/delete")
	public String delete(@RequestBody(required = true) CarDeleteDto dto) {
		return service.delete(dto);
	}
}
