package com.firdose.ars.exceptions;

import com.firdose.ars.dto.ErrorInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(UserNotFoundException.class)
    public @ResponseBody ErrorInfo UserNotFoundException(UserNotFoundException ex, HttpServletRequest req) {
        return new ErrorInfo(LocalDateTime.now(),ex.getMessage(),req.getRequestURI());
    }

    @ExceptionHandler(AirportNotFoundException.class)
    public @ResponseBody ErrorInfo AirportNotFoundException(AirportNotFoundException ex,HttpServletRequest req) {
        return new ErrorInfo(LocalDateTime.now(),ex.getMessage(),req.getRequestURI());
    }

    @ExceptionHandler(FlightNotFoundException.class)
    public @ResponseBody ErrorInfo FlightNotFoundException(FlightNotFoundException ex,HttpServletRequest req) {
        return new ErrorInfo(LocalDateTime.now(),ex.getMessage(),req.getRequestURI());
    }

    @ExceptionHandler(ScheduledFlightIdNotFoundException.class)
    public @ResponseBody ErrorInfo ScheduledFlightIdNotFoundException(ScheduledFlightIdNotFoundException e,HttpServletRequest req) {
        return new ErrorInfo(LocalDateTime.now(),e.getMessage(),req.getRequestURI());
    }
}

