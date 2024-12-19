package com.fleet.vehicle_routing.solver;

import ai.timefold.solver.core.api.score.buildin.hardsoftlong.HardSoftLongScore;
import ai.timefold.solver.core.api.score.stream.Constraint;
import ai.timefold.solver.core.api.score.stream.ConstraintFactory;
import ai.timefold.solver.core.api.score.stream.ConstraintProvider;
import com.fleet.vehicle_routing.domain.Vehicle;
import com.fleet.vehicle_routing.domain.Visit;
import com.fleet.vehicle_routing.domain.projectDomains.Task;
import org.jspecify.annotations.NonNull;

public class VehicleRoutingConstraintProvider implements ConstraintProvider {
    @Override
    public Constraint @NonNull [] defineConstraints(@NonNull ConstraintFactory factory) {
        return new Constraint[]{
                vehicleCapacity(factory),
//                serviceFinishedAfterMaxEndTime(factory),
                minimizeTravelTime(factory),
                visitShouldHaveVehicle(factory)
        };
    }

    // defining constraints

    public Constraint vehicleCapacity(ConstraintFactory factory){
        return factory
                .forEach(Vehicle.class)
                .filter(vehicle -> vehicle.getCapacity() < vehicle.getTotalDemand())
                .penalize(HardSoftLongScore.ONE_HARD,
                        vehicle -> vehicle.getTotalDemand() - vehicle.getCapacity())
                .asConstraint("vehicleCapacity");
    }
    public Constraint visitShouldHaveVehicle(ConstraintFactory factory){
        return factory
                .forEachIncludingUnassigned(Visit.class)
                .filter(visit -> visit.getVehicle()==null)
                .penalize(HardSoftLongScore.ofSoft(10))
                .asConstraint("visitShouldHaveVehicle");
    }


    public Constraint minimizeTravelTime(ConstraintFactory factory){
        return factory
                .forEach(Vehicle.class)
                .penalizeLong(HardSoftLongScore.ONE_SOFT,Vehicle::getTotalDrivingTimeSeconds)
                .asConstraint("minimizeTravelTime");
    }


   public Constraint serviceFinishedAfterMaxEndTime(ConstraintFactory factory){
        return factory
                .forEach(Visit.class)
                .filter(Visit::isServiceFinishedAfterMaxEndTime)
                .penalize(HardSoftLongScore.ofSoft(10))
                .asConstraint("serviceFinishedAfterMaxEndTime");
   }



}
