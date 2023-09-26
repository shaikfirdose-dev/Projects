package com.firdose.ars.dto;

import javax.persistence.*;

@Entity
@Table(name="scheduled_flights")
public class ScheduleFlight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int scheduledFlightId;

    @OneToOne(cascade = CascadeType.ALL)
    Flight flight;

    private int availableSeats;

    private int price;

    @OneToOne(cascade = CascadeType.ALL)
    Schedule schedule;



    public ScheduleFlight() {

    }


    public ScheduleFlight(int scheduledFlightId, Flight flight, int availableSeats, int price, Schedule schedule) {
        super();
        this.scheduledFlightId = scheduledFlightId;
        this.flight = flight;
        this.availableSeats = availableSeats;
        this.price = price;
        this.schedule = schedule;
    }


    public int getScheduledFlightId() {
        return scheduledFlightId;
    }

    public void setScheduledFlightId(int scheduledFlightId) {
        this.scheduledFlightId = scheduledFlightId;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }


    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "ScheduleFlight [scheduledFlightId="+ scheduledFlightId + ", flight=" + flight + ", availableSeats="
                + availableSeats + ", price=" + price + ", schedule=" + schedule + "]";
    }

}
