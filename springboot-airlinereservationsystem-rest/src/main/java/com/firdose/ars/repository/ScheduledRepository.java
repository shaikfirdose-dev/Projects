package com.firdose.ars.repository;

import com.firdose.ars.dto.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledRepository extends JpaRepository<Schedule, Integer> {

}
