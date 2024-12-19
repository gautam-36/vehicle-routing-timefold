package com.fleet.vehicle_routing.domain.projectDomains;

import ai.timefold.solver.core.api.domain.lookup.PlanningId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {

    private String taskTitle;
    private String performer;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String address;
    private String latitude;
    private String longitude;
    private String description;
    private Duration duration;
    private String orderId;
    private Integer capacity;
    private Boolean freezer;


}
