package com.firdose.ars.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="schedule")
public class Schedule {
    @Id
    private int scheduleId;

    @OneToOne(cascade = CascadeType.ALL)
    Airport srcLocation;

    @OneToOne(cascade = CascadeType.ALL)
    Airport destLocation;

    @Temporal(TemporalType.DATE)
    private Date depatDate;

    @Temporal(TemporalType.DATE)
    private Date arrDate;

    private String duration;

    public Schedule() {

    }

    public Schedule(int scheduleId, Airport srcLocation, Airport destLocation, Date depatDate, Date arrDate,
                       String duration) {
        super();
        this.scheduleId = scheduleId;
        this.srcLocation = srcLocation;
        this.destLocation = destLocation;
        this.depatDate = depatDate;
        this.arrDate = arrDate;
        this.duration = duration;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Airport getSrcLocation() {
        return srcLocation;
    }

    public void setSrcLocation(Airport srcLocation) {
        this.srcLocation = srcLocation;
    }

    public Airport getDestLocation() {
        return destLocation;
    }

    public void setDestLocation(Airport destLocation) {
        this.destLocation = destLocation;
    }

    public Date getDepatDate() {
        return depatDate;
    }

    public void setDepatDate(Date depatDate) {
        this.depatDate = depatDate;
    }

    public Date getArrDate() {
        return arrDate;
    }

    public void setArrDate(Date arrDate) {
        this.arrDate = arrDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "ScheduleDTO [scheduleId=" + scheduleId + ", srcLocation=" + srcLocation + ", destLocation="
                + destLocation + ", depatDate=" + depatDate + ", arrDate=" + arrDate + ", duration=" + duration + "]";
    }
}
