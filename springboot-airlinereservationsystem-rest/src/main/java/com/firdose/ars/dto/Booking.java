package com.firdose.ars.dto;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="bookings")
public class Booking {

    @Id
    private int bookingId;
    @Temporal(TemporalType.DATE)
    private Date bookingDate;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "bookings")
    @JsonIgnore
    Set<Passenger> passengers=new HashSet<>();

    @ManyToOne
    ScheduleFlight scheduleFlight;

    public Booking() {

    }

    public Booking(int bookingId, Date bookingDate, Set<Passenger> passengers) {
        super();
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.passengers = passengers;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int i) {
        this.bookingId = i;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }


    public ScheduleFlight getScheduleFlight() {
        return scheduleFlight;
    }

    public void setScheduleFlight(ScheduleFlight scheduleFlight) {
        this.scheduleFlight = scheduleFlight;
    }

    @Override
    public String toString() {
        return "BookingDTO [bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", passengers=" + passengers
                + ", scheduleFlight=" + scheduleFlight + "]";
    }
}
