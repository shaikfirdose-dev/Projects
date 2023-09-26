package com.firdose.ars.controller;

import com.firdose.ars.service.impl.BookingServiceImp;
import com.firdose.ars.service.impl.PassengerServiceImp;
import com.firdose.ars.service.impl.UserServiceImp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {
    @Autowired
    BookingServiceImp bookingServiceImp;
    @Autowired
    UserServiceImp userServiceImp;
    @Autowired
    PassengerServiceImp passengerServiceImp;

    private static final Log log= LogFactory.getLog(BookingController.class);

    @GetMapping("/view-all-bookings")
    public ResponseEntity<?> viewAllBookings(){
        log.info("All booking information");
        return new ResponseEntity<>(bookingServiceImp.viewAllBookings(), HttpStatus.OK);
    }

    @GetMapping("/display-all-bookings")
    public ResponseEntity<?> viewUserBookings(){
        log.info("All users booking details");
        return new ResponseEntity<>(userServiceImp.viewAllBookings(), HttpStatus.OK);
    }


}