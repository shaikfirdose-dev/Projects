package com.firdose.ars.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "airports")
public class Airport {

    @Id
    private String airportCode;
    private String airportLocation;
    private String airportName;

    public Airport() {

    }

    public Airport(String airportCode, String airportLocation, String airportName) {
        super();
        this.airportCode = airportCode;
        this.airportLocation = airportLocation;
        this.airportName = airportName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportLocation() {
        return airportLocation;
    }

    public void setAirportLocation(String airportLocation) {
        this.airportLocation = airportLocation;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    @Override
    public String toString() {
        return "AirportDTO [airportCode=" + airportCode + ", airportLocation=" + airportLocation + ", airportName="
                + airportName + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return Objects.equals(airportCode, airport.airportCode) && Objects.equals(airportLocation, airport.airportLocation) && Objects.equals(airportName, airport.airportName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airportCode, airportLocation, airportName);
    }
}
