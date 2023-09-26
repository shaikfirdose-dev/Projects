package com.firdose.ars.controller;

import com.firdose.ars.dto.Flight;
import com.firdose.ars.service.impl.FlightServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    @Autowired
    FlightServiceImp flightServiceImp;



    @PostMapping("/add-flight")
    public ResponseEntity<?> addFlight(@RequestBody Flight flight) throws Exception{
        Flight fligt=flightServiceImp.addFlight(flight);
        EntityModel<Flight> fli=EntityModel.of(fligt);
        fli.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).viewFlights()).withRel("viewFlights"));
        fli.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).searchFlightByFlightName(flight.getFlightName())).withRel("searchFlightsByName"));
        fli.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).updateFlight(fligt)).withRel("updateFlight"));
        fli.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteFlightByFlightNumber(fligt.getFlightNumber())).withRel("deleteFlightByFlightNumber"));
        return new ResponseEntity<>(fli, HttpStatus.CREATED);
    }

    @GetMapping("/view-flights")
    public ResponseEntity<?> viewFlights(){
        return ResponseEntity.ok(flightServiceImp.viewFlights());
    }

    @PutMapping("/update-flight")
    public ResponseEntity<?> updateFlight(@RequestBody Flight flight) throws Exception{
        Flight fl=flightServiceImp.updateFlight(flight);
        EntityModel<Flight> flt=EntityModel.of(fl);
        flt.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).searchFlightByFlightNumber(flight.getFlightName())).withRel("searchFlightByFlightNumber"));
        flt.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).viewFlights()).withRel("viewFlights"));
        flt.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).searchFlightByFlightName(flight.getFlightName())).withRel("searchFlightsByName"));
        flt.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).addFlight(fl)).withRel("addFlight"));
        flt.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteFlightByFlightNumber(fl.getFlightNumber())).withRel("deleteFlightByFlightNumber"));

        return new ResponseEntity<>(flt, HttpStatus.OK);
    }

    @GetMapping("/search-flights/{flightName}")
    public ResponseEntity<?> searchFlightByFlightName(@PathVariable("flightName") String flightName){
        return new ResponseEntity<>(flightServiceImp.searchFlightsByFlightName(flightName), HttpStatus.FOUND);
    }

    @GetMapping("/search-flightByFlightNumber/{flightNumber}")
    public ResponseEntity<?> searchFlightByFlightNumber(@PathVariable("flightNumber") String flightNumber) throws Exception{
        Flight flight=flightServiceImp.searchFlightByFlightNumber(flightNumber);
        EntityModel<Flight> fl=EntityModel.of(flight);
        fl.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).viewFlights()).withRel("viewFlights"));
        fl.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).searchFlightByFlightName(flight.getFlightName())).withRel("searchFlightsByName"));
        fl.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).updateFlight(flight)).withRel("updateFlight"));
        fl.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteFlightByFlightNumber(flight.getFlightNumber())).withRel("deleteFlightByFlightNumber"));
        fl.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).addFlight(flight)).withRel("addFlight"));

        return new ResponseEntity<>(fl, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-flight/{flightNumber}")
    public ResponseEntity<?> deleteFlightByFlightNumber(@PathVariable("flightNumber") String flightNumber) throws Exception{
        Flight fl=flightServiceImp.deleteFlightByFlightNumber(flightNumber);
        EntityModel<Flight> flt=EntityModel.of(fl);
        flt.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).viewFlights()).withRel("viewFlights"));
        flt.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).searchFlightByFlightNumber(fl.getFlightName())).withRel("searchFlightByFlightNumber"));
        flt.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).searchFlightByFlightName(fl.getFlightName())).withRel("searchFlightsByName"));
        flt.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).addFlight(fl)).withRel("addFlight"));
        return new ResponseEntity<>(flt, HttpStatus.OK);
    }

    @DeleteMapping("/delete-flights-by-name/{flightName}")
    public ResponseEntity<?> deleteFlightsByFlightName(@PathVariable("flightName") String flightName){
        return new ResponseEntity<>(flightServiceImp.deleteFlightsByFlightName(flightName), HttpStatus.OK);

    }

}
