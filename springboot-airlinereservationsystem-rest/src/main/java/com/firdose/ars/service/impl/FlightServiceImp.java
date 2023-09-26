package com.firdose.ars.service.impl;

import com.firdose.ars.dto.Flight;
import com.firdose.ars.exceptions.FlightNotFoundException;
import com.firdose.ars.repository.FlightRepository;
import com.firdose.ars.service.FlightService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImp implements FlightService {

    @Autowired
    FlightRepository flightRepository;

    private static final Log log = LogFactory.getLog(FlightServiceImp.class);

    public Flight addFlight(Flight flight) {
        log.info("Flight added successfully");
        flightRepository.save(flight);
        return flight;
    }

    public List<Flight> viewFlights() {
        log.fatal("View all flights");
        return flightRepository.findAll();
    }

    public Flight updateFlight(Flight flight) {
        log.info("Flight updated successfully");
        flightRepository.save(flight);
        return flight;
    }

    public List<Flight> searchFlightsByFlightName(String flightName) {
        log.info("Flights are available");
        return flightRepository.searchFlightsByFlightName(flightName);
    }

    public Flight searchFlightByFlightNumber(String flightNumber) throws FlightNotFoundException {
        if (flightRepository.existsById(flightNumber)) {
            log.info("Flight is found");
            Flight flight = flightRepository.findById(flightNumber).get();
            return flight;
        } else {
            log.warn("Flight number is not found");
            throw new FlightNotFoundException("Flight not found,please enter correct flight number");
        }

    }

    public Flight deleteFlightByFlightNumber(String flightNumber) throws FlightNotFoundException {
        if (flightRepository.existsById(flightNumber)) {
            Flight flight = flightRepository.findById(flightNumber).get();
            flightRepository.deleteById(flightNumber);
            return flight;
        } else {
            log.warn("Flight number is not found");
            throw new FlightNotFoundException("Flight not found,please enter correct flight number");
        }

    }


    public List<Flight> deleteFlightsByFlightName(String flightName) {
        log.info("Flights are deleted");
        List<Flight> flights = flightRepository.searchFlightsByFlightName(flightName);

        for (Flight fl : flights) {
            log.warn("Deleting all flights with same name");
            flightRepository.deleteById(fl.getFlightNumber());
        }
        return flights;
    }

}







