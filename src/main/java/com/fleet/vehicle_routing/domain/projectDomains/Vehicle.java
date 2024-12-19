package com.fleet.vehicle_routing.domain.projectDomains;

import ai.timefold.solver.core.api.domain.entity.PlanningEntity;
import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import ai.timefold.solver.core.api.domain.variable.PlanningVariable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@PlanningEntity
public class Vehicle {

    @PlanningId
    private int id;
    private String name;
    private String status;
    private String licensePlate;
    private String vin;
    private String color;
    private String type;
    private String subtype;
    private String object;
    private String startDepot;
    private String endDepot;
    private Boolean freezer;
    private List<String> tags;
    private String workStart;
    private String workFinish;
    private Integer cargoCapacityPallets;
    private Integer cargoCapacityLiters;
    private Integer cargoCapacityKg;

//
//    @PlanningVariable
//    List<Task>tasks;

}
