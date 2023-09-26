package com.firdose.ars.controller;

import com.firdose.ars.dto.ScheduleFlight;
import com.firdose.ars.dto.User;
import com.firdose.ars.exceptions.UserNotFoundException;
import com.firdose.ars.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    BookingServiceImp bookingServiceImp;

    @Autowired
    PassengerServiceImp passengerServiceImp;

    @Autowired
    LoginServiceImp loginServiceImp;

    @Autowired
    ScheduledFlightServiceImp scheduledFlightServiceImp;

    @Autowired
    FlightServiceImp flightServiceImp;


    @PostMapping("/add-user-booking")
    public ResponseEntity<?> addUserBookings(@RequestBody User adduserbookings) throws Exception{
        try {
            if(loginServiceImp.searchByUserName(adduserbookings.getUserName())) {
                userServiceImp.addUserBookings(adduserbookings);
            }

        }
        catch(UserNotFoundException e) {
            return new ResponseEntity<>("Oops!,username not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(adduserbookings, HttpStatus.OK);
    }

    @GetMapping("/view-all-scheduled-flights")
    public ResponseEntity<?> viewAllScheduledFlights(){
        return new ResponseEntity<>(scheduledFlightServiceImp.getScheduledFlights(), HttpStatus.OK);
    }

    @GetMapping("/view-user-bookings/{username}")
    public ResponseEntity<?> viewUserBookings(@PathVariable("username") String username) throws Exception{
        return new ResponseEntity<>(userServiceImp.viewUserBookings(username), HttpStatus.OK);
    }

    @PutMapping("/update-user-bookings")
    public ResponseEntity<?> updateUserBookings(@RequestBody User user){
        User us=userServiceImp.updateUserBookings(user);
        EntityModel<User> em=EntityModel.of(us);
        em.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).viewAllScheduledFlights()).withRel("viewAllScheduledFlights"));
        em.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteUserBookings(us.getUserName())).withRel("deleteUserBookings"));
        return new ResponseEntity<>(em, HttpStatus.OK);
    }

    @DeleteMapping("/delete-user-bookings/{username}")
    public ResponseEntity<?> deleteUserBookings(@PathVariable("username") String username) {
        if(userServiceImp.deleteUserBookings(username)) {
            return new ResponseEntity<>("User booking cancelled successfully, your amount will be refunded soon", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Username not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/display-scheduled-flight")
    public ResponseEntity<?> displayScheduledFlightByLocation(@RequestParam("source") String source,@RequestParam("destination") String destination){
        List<ScheduleFlight> flights=scheduledFlightServiceImp.searchScheduledFlightsByLocation(source,destination);
        if(flights!=null && !flights.isEmpty()) {
            return new ResponseEntity<>(flights, HttpStatus.FOUND);
        }
        else {
            return new ResponseEntity<>("Sorry, no flights are avilable", HttpStatus.NOT_FOUND);
        }
    }


}

