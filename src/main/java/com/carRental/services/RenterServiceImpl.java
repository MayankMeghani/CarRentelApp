package com.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carRental.dao.RenterRepository;
import com.carRental.entities.Renter;

@Service
public class RenterServiceImpl implements RenterService{
		
		@Autowired
		private RenterRepository renterRepository;
		
		public RenterServiceImpl(RenterRepository renterRepository) {
			super();
			this.renterRepository = renterRepository;
		}

		@Override
		public void deleteById(int theId) {
			renterRepository.deleteById(theId);
			
		}

		@Override
		public void save(Renter theRenter) {
			 renterRepository.save(theRenter);
		}


		@Override
		public List<Renter> findAll() {
			return renterRepository.findAll();
		}

		@Override
		public Renter findById(int theId) {
			Optional<Renter> result = renterRepository.findById(theId);
			
			Renter theRenter = null;
			
			if (result.isPresent()) {
				theRenter = result.get();
			}
			else {
				throw new RuntimeException("Did not find renter id - " + theId);
			}
			
			return theRenter;
		}

}


