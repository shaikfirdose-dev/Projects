package com.firdose.ars.service;

import com.firdose.ars.dto.ScheduleFlight;
import com.firdose.ars.exceptions.ScheduledFlightIdNotFoundException;

import java.util.List;

public interface ScheduledFlightService {
    public ScheduleFlight addScheduledFlight(ScheduleFlight sf);
    public List<ScheduleFlight> getScheduledFlights();
    public ScheduleFlight viewScheduledFlight(int scheduledFlightId) throws ScheduledFlightIdNotFoundException;
    public ScheduleFlight updateScheduledFlight(ScheduleFlight sf);
    public List<ScheduleFlight> searchScheduledFlightsByLocation(String source, String destination);
}
