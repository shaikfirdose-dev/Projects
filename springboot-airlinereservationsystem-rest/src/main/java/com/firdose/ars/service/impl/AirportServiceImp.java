package com.firdose.ars.service.impl;

import com.firdose.ars.dto.Airport;
import com.firdose.ars.exceptions.AirportNotFoundException;
import com.firdose.ars.repository.AirportRepository;
import com.firdose.ars.service.AirportService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImp implements AirportService {
    @Autowired
    AirportRepository airportRepository;

    private static final Log log= LogFactory.getLog(AirportServiceImp.class);

    public Airport addAirport(Airport airport) {
        log.info("Airport added successfully");
        airportRepository.save(airport);
        return airport;
    }

    public List<Airport> viewAirports() {
        log.fatal("All airports");
        return airportRepository.findAll();
    }

    public Airport modifyAirport(Airport airport) {
        log.warn("Airport updated successfully");
        return airportRepository.save(airport);
    }

    public Airport deleteAirport(String code) throws AirportNotFoundException {
        if(airportRepository.existsById(code)) {
            log.info("Airport deleted successfully");
            Airport airport=airportRepository.findById(code).get();
            airportRepository.deleteById(code);
            return airport;
        }
        else {
            log.error("Airport not found");
            throw new AirportNotFoundException("No airport is not available with this code, please enter correct code");
        }

    }

    public Airport searchAirport(String code) throws AirportNotFoundException{
        if(airportRepository.existsById(code)) {
            log.info("Airport searched successfully");
            Airport airport=airportRepository.findById(code).get();
            return airport;
        }
        else {
            log.error("Airport not found with this code");
            throw new AirportNotFoundException("No airport is not available with this code, please enter correct code");
        }
    }




}
