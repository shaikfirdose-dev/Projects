package com.firdose.ars.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="flight")
public class Flight {
    @Id
    private String flightNumber; // IN-382
    private String flightCarrierName;
    private String flightName;
    private int seatCapacity;


    public Flight() {

    }

    public Flight(String flightNumber, String flightCarrierName, String flightName, int seatCapacity) {
        super();
        this.flightNumber = flightNumber;
        this.flightCarrierName = flightCarrierName;
        this.flightName = flightName;
        this.seatCapacity = seatCapacity;

    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getFlightCarrierName() {
        return flightCarrierName;
    }

    public void setFlightCarrierName(String flightCarrierName) {
        this.flightCarrierName = flightCarrierName;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }





    @Override
    public String toString() {
        return "FlightDTO [flightNumber=" + flightNumber + ", flightCarrierName=" + flightCarrierName + ", flightName="
                + flightName + ", seatCapacity=" + seatCapacity + "]";
    }




}