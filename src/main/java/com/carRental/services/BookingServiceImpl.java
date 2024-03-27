package com.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.carRental.dao.BookingRepository;
import com.carRental.entities.Booking;
import com.carRental.entities.Car;
import com.carRental.entities.Person;
import com.carRental.exception.NotFoundException;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	BookingRepository recordRepository;

	public BookingServiceImpl(BookingRepository recordRepository) {
		super();
		this.recordRepository = recordRepository;
	}

	@Override
	public List<Booking> findAll() {
		List<Booking> bookings =recordRepository.findAll();
		if(bookings.isEmpty()) {
			throw new NotFoundException("Did not find any record");
		}
		return bookings;	
	}

	@Override
	public Booking findById(int theId) {
		Optional<Booking> result = recordRepository.findById(theId);
		
		Booking theRecord = null;
		
		if (result.isPresent()) {
			theRecord = result.get();
		}
		else {
			throw new NotFoundException("Did not find record with given id");
		}
		
		return theRecord;

	}

	@Override
	public void save(Booking record) {
		recordRepository.save(record);
	}

	@Override
	public void deleteById(int theId) {
		recordRepository.deleteById(theId);
	}

	@Override
	public Booking findByCar(Car car) {
		Booking record =recordRepository.findByCar(car);
		if(record==null) {
			throw new NotFoundException("Did not find record for given car");			
		}
		else 
			return record;
	}

	@Override
	public List<Booking> findByCustomer(Person customer) {
		List<Booking> bookings= recordRepository.findByCustomer(customer);
		if(bookings.isEmpty()) {
			throw new NotFoundException("Did not find record for given customer");	
		}
		else {
			return bookings;
		}
	}
	

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleBookingException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
