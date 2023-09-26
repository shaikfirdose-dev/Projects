package com.firdose.ars.service.impl;

import com.firdose.ars.dto.Booking;
import com.firdose.ars.repository.BookingRepository;
import com.firdose.ars.service.BookingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImp implements BookingService {
    @Autowired
    BookingRepository bookingRepository;

    private static final Log log= LogFactory.getLog(BookingServiceImp.class);

    public void addBookings(Booking booking) {
        log.info("Booking added successfully "+booking.getBookingId());
        bookingRepository.save(booking);
    }

    public List<Booking> viewAllBookings() {
        log.info("View all bookings");
        return bookingRepository.findAll();
    }

}
