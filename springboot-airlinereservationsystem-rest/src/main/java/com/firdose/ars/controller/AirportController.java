package com.firdose.ars.controller;

import com.firdose.ars.dto.Airport;
import com.firdose.ars.exceptions.AirportNotFoundException;
import com.firdose.ars.service.impl.AirportServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/airport")
public class AirportController {

    @Autowired
    AirportServiceImp service;

    @PostMapping("/add-airport")
    public ResponseEntity<?> addAirport(@RequestBody Airport airport) {
        return new ResponseEntity<>(service.addAirport(airport), HttpStatus.OK);
    }

    @GetMapping("/view-airports")
    public List<Airport> viewAirports(){
        return service.viewAirports();
    }


    @PutMapping("/update-airport")
    public ResponseEntity<?> updateAirport(@RequestBody Airport airport) throws AirportNotFoundException {
        Airport airportDTO=service.modifyAirport(airport);
        EntityModel<?> response=EntityModel.of(airportDTO);
        response.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).deleteAirport(airportDTO.getAirportCode())).withRel("searchairport/{code}"));
        response.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).searchAirport(airportDTO.getAirportCode())).withRel("searchairport/{code}"));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete-airport/{code}")
    public ResponseEntity<?> deleteAirport(@PathVariable("code") String code) throws AirportNotFoundException {
        service.deleteAirport(code);
        return new ResponseEntity<>("Airport deleted succcessfully", HttpStatus.OK);
    }

    @GetMapping("/search-airport/{code}")
    public ResponseEntity<?> searchAirport(@PathVariable("code") String code) throws AirportNotFoundException {
        Airport airportDTO=service.searchAirport(code);
        EntityModel<Airport> airEntityModel=EntityModel.of(airportDTO);
        airEntityModel.add(WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).viewAirports()).withRel("viewairports"));
        return new ResponseEntity<>(airEntityModel, HttpStatus.FOUND);
    }

}
