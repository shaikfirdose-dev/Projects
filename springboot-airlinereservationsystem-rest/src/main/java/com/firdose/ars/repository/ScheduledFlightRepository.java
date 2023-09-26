package com.firdose.ars.repository;

import com.firdose.ars.dto.ScheduleFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduledFlightRepository extends JpaRepository<ScheduleFlight, Integer> {

        @Query("select sf from ScheduleFlightDTO sf where sf.schedule.scheduleId in (select s from ScheduleDTO s where s.srcLocation.airportLocation=:src and s.destLcosation.airportLocation=:dest)")
        public List<ScheduleFlight> searchScheduledFlightBySourceAndDestination(@Param("src") String source, @Param("dest") String destination);

        @Query("select sf from ScheduleFlightDTO sf where sf.schedule.scheduleId in (select s from ScheduleDTO s where s.srcLocation.airportLocation=:src)")
        public List<ScheduleFlight> searchScheduledFlightBySource(@Param("src") String source);

        @Query("select sf from ScheduleFlightDTO sf where sf.schedule.scheduleId in (select s from ScheduleDTO s where s.destLcosation.airportLocation=:dest)")
        public List<ScheduleFlight> seachScheduledFlightByDestination(@Param("dest") String destination);


}
