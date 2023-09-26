package com.firdose.ars.repository;

import com.firdose.ars.dto.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {

    @Query("select f from FlightDTO f where f.flightName=:f1")
    public List<Flight> searchFlightsByFlightName(@Param("f1") String flightName);

}
