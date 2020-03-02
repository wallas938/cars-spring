package com.renault.renaultspring.services.car;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.renault.renaultspring.dtos.CarCreateDto;
import com.renault.renaultspring.dtos.CarDeleteDto;
import com.renault.renaultspring.dtos.CarDto;
import com.renault.renaultspring.entities.Car;
import com.renault.renaultspring.repositories.car.CarJpaRepository;

@Service
public class CarServiceImpl implements CarService {

	public final CarJpaRepository repository;

	public CarServiceImpl(CarJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<CarDto> get(String brand) {
		List<Car> cars = repository.findByBrand(brand);
		List<CarDto> carDtos = new ArrayList<CarDto>();
		for (int i = 0; i < cars.size(); i++) {
			CarDto carDto = new CarDto();
			carDto.setBrand(cars.get(i).getBrand());
			carDto.setModel(cars.get(i).getModel());
			carDtos.add(carDto);
		}
		return carDtos;

	}

	public String post(CarCreateDto dto) {
		String brand = dto.getBrand();
		String model = dto.getModel();
		try {
			if(isBrandExist(brand)) {
				if (repository.saveCar(brand, model) == 1) {
					return "Le modele " + model + " de marque " + brand + " a été enregistré !";
				}
			}
			return "La marque de votre véhicule est inexistant";
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Un problème est survenu lors de l'enregistrement de votre véhicule";

	}

	public String delete(CarDeleteDto dto) {
		String column = dto.getColumn();
		String value = dto.getValue();
		int nbr = 0;
		if(column.equalsIgnoreCase("brand")) {
			nbr = repository.deleteCarBrand(value);
			if(nbr > 0) {
				return nbr > 1 ? nbr + " véhicules de marque: " + value + " ont été suprimés!" : "le véhicule de marque " + value + " été suprimé";
			}
		} else if(column.equalsIgnoreCase("model")){
			nbr = repository.deleteCarModel(value);
			 if(nbr > 0){
				return nbr == 1 ? nbr + " " + value + " ont été suprimés!" : value + " suprimé";
			}
		}
		return "Une Erreur c'est produite lors de la suppression";
	}
	
	private Set<String> getBrands() throws FileNotFoundException {
    	InputStream resource = CarServiceImpl.class.getClassLoader().getResourceAsStream("static/brands.txt");
	        Set<String> brands = new HashSet<>();
	        for (String line : new BufferedReader(new InputStreamReader(resource)).lines().collect(toList())) {
	            String[] column = line.split("\n\r");
	            String replace = column[0].replace("\"", "");
	            brands.add(replace);
	    }
	    return brands;
	}
	
	private boolean isBrandExist(String brandRequested) throws FileNotFoundException {
		Set<String> brands = getBrands();
		for (String brand : brands) {
			if(brand.equalsIgnoreCase(brandRequested)) {
				return true;
			}
		}
		return false;
	}
}
