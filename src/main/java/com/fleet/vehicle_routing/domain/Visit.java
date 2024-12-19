package com.fleet.vehicle_routing.domain;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.CascadingUpdateShadowVariable;
import ai.timefold.solver.core.api.domain.variable.InverseRelationShadowVariable;
import ai.timefold.solver.core.api.domain.variable.PreviousElementShadowVariable;


import java.time.Duration;
import java.time.LocalDateTime;

@PlanningEntity
public class Visit {
    @PlanningId
    private int id;

    private String name;
    private Location location;
    private int demand;
    private LocalDateTime minStartTime;
    private LocalDateTime maxEndTime;
    private Duration serviceDuration;

    @InverseRelationShadowVariable(sourceVariableName = "visits")
    private Vehicle vehicle;

    @PreviousElementShadowVariable(sourceVariableName = "visits")
    private Visit previousVisit;

    @CascadingUpdateShadowVariable(targetMethodName = "updateArrivalTime")
    private LocalDateTime arrivalTime;



    // constructors
    public Visit() {
    }

    public Visit(int id, String name, Location location, int demand,
                 LocalDateTime minStartTime, LocalDateTime maxEndTime, Duration serviceDuration) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.demand = demand;
        this.minStartTime = minStartTime;
        this.maxEndTime = maxEndTime;
        this.serviceDuration = serviceDuration;
    }

    private void updateArrivalTime() {
        if (previousVisit == null && vehicle == null) {
            arrivalTime = null;
            return;
        }
        LocalDateTime departureTime = previousVisit == null ? vehicle.getDepartureTime() : previousVisit.getDepartureTime();
        arrivalTime = departureTime != null ? departureTime.plusSeconds(getDrivingTimeSecondsFromPreviousStandstill()) : null;
    }

    public LocalDateTime getDepartureTime() {
        if (arrivalTime == null) {
            return null;
        }
        return getStartServiceTime().plus(serviceDuration);
    }

    public LocalDateTime getStartServiceTime() {
        if (arrivalTime == null) {
            return null;
        }
        return arrivalTime.isBefore(minStartTime) ? minStartTime : arrivalTime;
    }

    public boolean isServiceFinishedAfterMaxEndTime() {
        return arrivalTime != null
                && arrivalTime.plus(serviceDuration).isAfter(maxEndTime);
    }

    public long getServiceFinishedDelayInMinutes() {
        if (arrivalTime == null) {
            return 0;
        }
        return Duration.between(maxEndTime, arrivalTime.plus(serviceDuration)).toMinutes();
    }

    public long getDrivingTimeSecondsFromPreviousStandstill() {
        if (vehicle == null) {
            throw new IllegalStateException(
                    "This method must not be called when the shadow variables are not initialized yet.");
        }
        if (previousVisit == null) {
            return vehicle.getHomeLocation().getDrivingTimeTo(location);
        }
        return previousVisit.getLocation().getDrivingTimeTo(location);
    }


    // Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public LocalDateTime getMinStartTime() {
        return minStartTime;
    }

    public void setMinStartTime(LocalDateTime minStartTime) {
        this.minStartTime = minStartTime;
    }

    public LocalDateTime getMaxEndTime() {
        return maxEndTime;
    }

    public void setMaxEndTime(LocalDateTime maxEndTime) {
        this.maxEndTime = maxEndTime;
    }

    public Duration getServiceDuration() {
        return serviceDuration;
    }

    public void setServiceDuration(Duration serviceDuration) {
        this.serviceDuration = serviceDuration;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Visit getPreviousVisit() {
        return previousVisit;
    }

    public void setPreviousVisit(Visit previousVisit) {
        this.previousVisit = previousVisit;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
