package com.firdose.ars.service.impl;

import com.firdose.ars.dto.ScheduleFlight;
import com.firdose.ars.exceptions.ScheduledFlightIdNotFoundException;
import com.firdose.ars.repository.ScheduledFlightRepository;
import com.firdose.ars.service.ScheduledFlightService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduledFlightServiceImp implements ScheduledFlightService {
    @Autowired
    ScheduledFlightRepository scheduledFlightRepository;

    private static final Log log= LogFactory.getLog(ScheduledFlightServiceImp.class);

    public ScheduleFlight addScheduledFlight(ScheduleFlight sf) {
        scheduledFlightRepository.save(sf);
        log.info("Scheduled flight added successfully");
        return sf;
    }

    public List<ScheduleFlight> getScheduledFlights() {
        log.info("View all scheduled flights");
        return scheduledFlightRepository.findAll();
    }


    public ScheduleFlight viewScheduledFlight(int scheduledFlightId) throws ScheduledFlightIdNotFoundException {
        if(scheduledFlightRepository.existsById(scheduledFlightId)){
            log.debug("Scheduled flight id is found");
            Optional<ScheduleFlight> sf= scheduledFlightRepository.findById(scheduledFlightId);
            if(sf.isPresent()) {
                return sf.get();
            }
        }
        else {
            log.error("Scheduled flight id is not found");
            throw new ScheduledFlightIdNotFoundException("Scheduled Flight Id Not Found");
        }
        return null;
    }
    public ScheduleFlight updateScheduledFlight(ScheduleFlight sf) {
        scheduledFlightRepository.save(sf);
        log.info("Scheduled flight is updated");
        return sf;
    }

    public List<ScheduleFlight> searchScheduledFlightsByLocation(String source, String destination) {
        boolean isSourceValid = source!=null && source.trim()!="";
        boolean isDestinationValid = destination!=null && destination.trim()!="";

        if(isSourceValid && isDestinationValid) {
            log.info("Scheduled flights is available from "+source+" "+"to"+" "+destination);
            return scheduledFlightRepository.searchScheduledFlightBySourceAndDestination(source,destination);
        }
        else if(isSourceValid) {
            log.info("Scheduled flights is available from "+source);
            return scheduledFlightRepository.searchScheduledFlightBySource(source);
        }
        else if(isDestinationValid) {
            log.info("Scheduled flights is available to "+destination);
            return scheduledFlightRepository.seachScheduledFlightByDestination(destination);
        }
        else {
            log.info("No flights are available");
            return null;
        }
    }



}

