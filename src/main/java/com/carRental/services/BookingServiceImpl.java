package com.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carRental.dao.BookingRepository;
import com.carRental.entities.Booking;
import com.carRental.entities.Customer;

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
		return recordRepository.findAll();
	}

	@Override
	public Booking findById(int theId) {
		Optional<Booking> result = recordRepository.findById(theId);
		
		Booking theRecord = null;
		
		if (result.isPresent()) {
			theRecord = result.get();
		}
		else {
			throw new RuntimeException("Did not find record id - " + theId);
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

//	@Override
//	public List<Booking> findByUser(User user) {
//		return recordRepository.FindByUser(user);
//	}
	

//	@Override
//	public Booking findByCar(Car car) {
//		return recordRepository.FindByUser(car);
//	}

}
