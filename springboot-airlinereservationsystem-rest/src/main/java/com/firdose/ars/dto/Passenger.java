package com.firdose.ars.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name="passenger")
public class Passenger {
    @Id
    private int passengerId;
    private String passengerName;
    private int passengerAge;
    private String mobileNumber;
    private String passengerUIN;  // unique identification number
    private String luggage;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="bookingId")
    Set<Booking> bookings=new HashSet<>();

    public Passenger() {

    }



    public Passenger(int passengerId, String passengerName, int passengerAge, String mobileNumber,
                        String passengerUIN, String luggage, Set<Booking> bookings) {
        super();
        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.passengerAge = passengerAge;
        this.mobileNumber = mobileNumber;
        this.passengerUIN = passengerUIN;
        this.luggage = luggage;
        this.bookings = bookings;
    }



    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getPassengerAge() {
        return passengerAge;
    }

    public void setPassengerAge(int passengerAge) {
        this.passengerAge = passengerAge;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassengerUIN() {
        return passengerUIN;
    }

    public void setPassengerUIN(String passengerUIN) {
        this.passengerUIN = passengerUIN;
    }

    public String getLuggage() {
        return luggage;
    }

    public void setLuggage(String luggage) {
        this.luggage = luggage;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "PassengerDTO [passengerId=" + passengerId + ", passengerName=" + passengerName + ", passengerAge="
                + passengerAge + ", mobileNumber=" + mobileNumber + ", passengerUIN=" + passengerUIN + ", luggage="
                + luggage + ", bookings=" + bookings + "]";
    }


}