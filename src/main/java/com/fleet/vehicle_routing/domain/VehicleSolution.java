package com.fleet.vehicle_routing.domain;

import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.solution.PlanningEntityCollectionProperty;
import ai.timefold.solver.core.api.domain.solution.PlanningScore;
import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.domain.valuerange.ValueRangeProvider;
import ai.timefold.solver.core.api.score.buildin.hardsoft.HardSoftScore;
import ai.timefold.solver.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
import lombok.Getter;
import lombok.Value;

import java.util.List;
import java.util.stream.Stream;


@PlanningSolution
public class VehicleSolution {

    @PlanningId
    int id;

    @PlanningEntityCollectionProperty
    private List<Vehicle> vehicles;

    @PlanningEntityCollectionProperty
    @ValueRangeProvider
    private List<Visit> visits;

    @PlanningScore
    private HardSoftLongScore hardSoftScore;


    public VehicleSolution() {
    }

    public VehicleSolution(int id, List<Visit> visits, List<Vehicle> vehicles) {
        this.id = id;
        this.visits = visits;
        this.vehicles = vehicles;
    }

    // TODO: Pre-Calculate Driving Time

//    List<Location> locations = Stream.concat(
//            vehicles.stream().map(Vehicle::getHomeLocation),
//            visits.stream().map(Visit::getLocation)).toList();
//
//    DrivingTimeCalculator drivingTimeCalculator = HaversineDrivingTimeCalculator.getInstance();
//        drivingTimeCalculator.initDrivingTimeMaps(locations);
//}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public HardSoftLongScore getHardSoftScore() {
        return hardSoftScore;
    }

    public void setHardSoftScore(HardSoftLongScore hardSoftScore) {
        this.hardSoftScore = hardSoftScore;
    }
}
