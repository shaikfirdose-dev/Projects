package com.firdose.ars.service;

import com.firdose.ars.dto.Flight;
import com.firdose.ars.exceptions.FlightNotFoundException;

import java.util.List;

public interface FlightService {

    public Flight addFlight(Flight flight);
    public List<Flight> viewFlights();
    public Flight updateFlight(Flight flight);
    public List<Flight> searchFlightsByFlightName(String flightName);
    public Flight searchFlightByFlightNumber(String flightNumber) throws FlightNotFoundException;
    public Flight deleteFlightByFlightNumber(String flightNumber) throws FlightNotFoundException;
    public List<Flight> deleteFlightsByFlightName(String flightName);
}
