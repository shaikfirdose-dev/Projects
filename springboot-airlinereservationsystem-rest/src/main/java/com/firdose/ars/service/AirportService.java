package com.firdose.ars.service;

import com.firdose.ars.dto.Airport;
import com.firdose.ars.exceptions.AirportNotFoundException;

import java.util.List;

public interface AirportService {

    public Airport addAirport(Airport airport);
    public List<Airport> viewAirports();
    public Airport modifyAirport(Airport airport);
    public Airport deleteAirport(String code) throws AirportNotFoundException;
    public Airport searchAirport(String code) throws AirportNotFoundException;
}
