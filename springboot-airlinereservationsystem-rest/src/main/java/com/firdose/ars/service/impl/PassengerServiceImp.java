package com.firdose.ars.service.impl;

import com.firdose.ars.dto.Passenger;
import com.firdose.ars.repository.PassengerRepository;
import com.firdose.ars.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImp implements PassengerService {
    @Autowired
    PassengerRepository passengerRepository;

    public void addPassenger(Passenger passenger) {

        passengerRepository.save(passenger);
    }


}
