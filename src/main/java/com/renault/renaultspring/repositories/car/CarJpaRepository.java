package com.renault.renaultspring.repositories.car;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.renault.renaultspring.dtos.CarCreateDto;
import com.renault.renaultspring.dtos.CarDto;
import com.renault.renaultspring.entities.Car;

public interface CarJpaRepository extends JpaRepository<Car, Long> {

	@Query( value = "SELECT * FROM cars WHERE brand = :brand",
			nativeQuery = true)
	List<Car> findByBrand(@Param("brand") String brand);
	
	@Modifying
	@Transactional
	@Query(	value = "INSERT INTO cars(id, brand, model) VALUES(null, :brand, :model)",
		   	nativeQuery = true)
	int saveCar(@Param("brand") String brand, @Param("model") String model);
	
	@Modifying
	@Transactional
	@Query(	value = "DELETE FROM cars WHERE brand = :value",
		   	nativeQuery = true)
	int deleteCarBrand(@Param("value") String value);
	
	@Modifying
	@Transactional
	@Query(	value = "DELETE FROM cars WHERE model = :value",
		   	nativeQuery = true)
	int deleteCarModel(@Param("value") String value);
}
