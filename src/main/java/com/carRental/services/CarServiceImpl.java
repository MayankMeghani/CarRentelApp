package com.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carRental.dao.CarRepository;
import com.carRental.entities.Car;
import com.carRental.entities.Renter;

@Service
public class CarServiceImpl implements CarService{
	
	@Autowired
	CarRepository carRepository;
	
	public CarServiceImpl(CarRepository carRepository) {
		super();
		this.carRepository = carRepository;
	}

	@Override
	public List<Car> findAll() {
		return carRepository.findAll();
	}

	@Override
	public Car findById(int theId) {
		Optional<Car> result = carRepository.findById(theId);
		
		Car theCar = null;
		
		if (result.isPresent()) {
			theCar = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find car id - " + theId);
		}
		
		return theCar;
		
	}

	@Override
	public void save(Car thecar) {
		carRepository.save(thecar);
	}

	@Override
	public void deleteById(int theId) {
		carRepository.deleteById(theId);
	}

	@Override
	public List<Car> findByRenter(Renter theRenter) {
		List<Car> cars= carRepository.findByRenter(theRenter);
		return cars;
	}

}
