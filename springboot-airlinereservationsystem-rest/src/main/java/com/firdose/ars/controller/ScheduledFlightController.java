package com.firdose.ars.controller;

import com.firdose.ars.dto.Schedule;
import com.firdose.ars.dto.ScheduleFlight;
import com.firdose.ars.exceptions.AirportNotFoundException;
import com.firdose.ars.exceptions.FlightNotFoundException;
import com.firdose.ars.exceptions.ScheduledFlightIdNotFoundException;
import com.firdose.ars.service.impl.AirportServiceImp;
import com.firdose.ars.service.impl.FlightServiceImp;
import com.firdose.ars.service.impl.ScheduledFlightServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/scheduled-flight")
public class ScheduledFlightController {
    @Autowired
    ScheduledFlightServiceImp scheduledFlightServiceImp;

    @Autowired
    FlightServiceImp flightServiceImp;

    @Autowired
    AirportServiceImp airportServiceImp;

    @PostMapping("/add-scheduled-flight")
    public ResponseEntity<?> addScheduledFlight(@RequestBody ScheduleFlight scheduledFlight) throws Exception{

        Schedule schedule = new Schedule();
        try {
            schedule.setSrcLocation(airportServiceImp.searchAirport(scheduledFlight.getSchedule().getSrcLocation().getAirportCode()));//source location
        } catch (AirportNotFoundException e) {
            return new ResponseEntity<>("Source Airport Not Found", HttpStatus.BAD_REQUEST);
        }
        try {
            schedule.setDestLocation(airportServiceImp.searchAirport(scheduledFlight.getSchedule().getDestLocation().getAirportCode()));
        } catch (AirportNotFoundException e) {
            return new ResponseEntity<>("Destination Airport Not Found", HttpStatus.BAD_REQUEST);
        }
        schedule.setScheduleId(scheduledFlight.getScheduledFlightId());
        schedule.setDepatDate(scheduledFlight.getSchedule().getDepatDate());
        schedule.setArrDate(scheduledFlight.getSchedule().getArrDate());
        schedule.setDuration(scheduledFlight.getSchedule().getDuration());

        try {
            scheduledFlight.setFlight(flightServiceImp.searchFlightByFlightNumber(scheduledFlight.getFlight().getFlightNumber()));
        } catch (FlightNotFoundException e1) {
            return new ResponseEntity<>("Flight Not Found", HttpStatus.BAD_REQUEST);
        }
        scheduledFlight.setSchedule(schedule);
        scheduledFlight.setAvailableSeats(scheduledFlight.getAvailableSeats());

        try {
            ScheduleFlight sf=scheduledFlightServiceImp.addScheduledFlight(scheduledFlight);
            EntityModel<ScheduleFlight> em=EntityModel.of(sf);
            em.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).allScheduleFlights()).withRel("allScheduledFlights"));
            em.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).displayAllScheduledFlightsByLocation(sf.getSchedule().getSrcLocation().getAirportLocation(),sf.getSchedule().getDestLocation().getAirportLocation())).withRel("displayAllScheduledFlightsByLocation"));
            em.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).updateScheduledFlight(sf)).withRel("updateScheduledFlight"));
            return new ResponseEntity<>(em,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding Flight." + e, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/view-scheduled-flights")
    public List<ScheduleFlight> allScheduleFlights(){
        return scheduledFlightServiceImp.getScheduledFlights();
    }

    @GetMapping("/view-scheduled-flight/{scheduledId}")
    public ResponseEntity<?> viewScheduledFlight(@PathVariable("scheduledId") int scheduledFlightId) throws ScheduledFlightIdNotFoundException {
        ScheduleFlight sf=scheduledFlightServiceImp.viewScheduledFlight(scheduledFlightId);
        EntityModel<ScheduleFlight> em=EntityModel.of(sf);
        em.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).allScheduleFlights()).withRel("allScheduleFlightDTO"));
        em.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).displayAllScheduledFlightsByLocation(sf.getSchedule().getSrcLocation().getAirportLocation(),sf.getSchedule().getDestLocation().getAirportLocation())).withRel("displayAllScheduledFlightsByLocation"));
        em.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).updateScheduledFlight(sf)).withRel("updateScheduledFlight"));
        return new ResponseEntity<>(em, HttpStatus.FOUND);
    }

    @PutMapping("/update-schedule-flight")
    public ResponseEntity<?> updateScheduledFlight(@RequestBody ScheduleFlight sf){
        ScheduleFlight sft=scheduledFlightServiceImp.updateScheduledFlight(sf);
        EntityModel<ScheduleFlight> em=EntityModel.of(sft);
        em.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).allScheduleFlights()).withRel("allScheduleFlightDTO"));
        em.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).displayAllScheduledFlightsByLocation(sft.getSchedule().getSrcLocation().getAirportLocation(),sft.getSchedule().getDestLocation().getAirportLocation())).withRel("displayAllScheduledFlightsByLocation"));
        return new ResponseEntity<>(em, HttpStatus.OK);
    }

    @GetMapping("/search-scheduled-flights-by-location")
    public ResponseEntity<?> displayAllScheduledFlightsByLocation(@RequestParam("source") String source,@RequestParam("destination") String destination){
        List<ScheduleFlight> flights=scheduledFlightServiceImp.searchScheduledFlightsByLocation(source,destination);
        if(flights!=null && !flights.isEmpty()) {
            return new ResponseEntity<>(flights, HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<>("Sorry, no flights are available", HttpStatus.NOT_FOUND);
        }
    }

}

