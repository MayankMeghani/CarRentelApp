package com.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.carRental.dao.CarRepository;
import com.carRental.entities.Car;
import com.carRental.entities.Person;
import com.carRental.exception.NotFoundException;

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
		List<Car> cars= carRepository.findAll();
		if(cars.isEmpty()) {
			throw new NotFoundException("Did not find car for given renter");
		}
		return cars;	
	}

	@Override
	public Car findById(int theId) {
		Optional<Car> result = carRepository.findById(theId);
		
		Car theCar = null;
		
		if (result.isPresent()) {
			theCar = result.get();
		}
		else {
			throw new NotFoundException("Did not find car with given id  ");
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
	public List<Car> findByRenter(Person theRenter) {
		List<Car> cars= carRepository.findByRenter(theRenter);
		if(cars.isEmpty()) {
			throw new NotFoundException("Did not find car for given renter");
		}
		return cars;
	}


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleBookingException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
