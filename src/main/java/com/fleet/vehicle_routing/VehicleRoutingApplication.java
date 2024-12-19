package com.fleet.vehicle_routing;

import ai.timefold.solver.core.api.solver.SolverFactory;
import ai.timefold.solver.core.config.solver.SolverConfig;
import com.fleet.vehicle_routing.domain.Location;
import com.fleet.vehicle_routing.domain.Vehicle;
import com.fleet.vehicle_routing.domain.VehicleSolution;
import com.fleet.vehicle_routing.domain.Visit;
import com.fleet.vehicle_routing.solver.VehicleRoutingConstraintProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class VehicleRoutingApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleRoutingApplication.class, args);

//		System.out.println(LocalDateTime.now());
//		SolverFactory<VehicleSolution> solverFactory = SolverFactory.create(new SolverConfig()
//				.withSolutionClass(VehicleSolution.class)
//				.withEntityClasses(Vehicle.class, Visit.class)
//				.withConstraintProviderClass(VehicleRoutingConstraintProvider.class)
//				.withTerminationSpentLimit(Duration.ofSeconds(2))); // Time limit increased
//
//		VehicleSolution problem = loadData();
//		VehicleSolution solution = solverFactory.buildSolver().solve(problem);
//		System.out.println("Optimized Solution:");
//		printSolution(solution);

	}


	public static VehicleSolution loadData(){

		// locations data
		List<Location> locations = List.of(
				new Location(1, 28.613939, 77.209023),  // Depot 1 (New Delhi)
				new Location(2, 19.076090, 72.877426),  // Customer 1 (Mumbai)
				new Location(3, 13.082680, 80.270718),  // Customer 2 (Chennai)
				new Location(4, 22.572645, 88.363892),  // Customer 3 (Kolkata)
				new Location(5, 25.594095, 85.137566),  // Customer 4 (Patna)
				new Location(6, 23.8103, 90.4125),      // Customer 5 (Dhaka)
				new Location(7, 12.971599, 77.594566),  // Customer 6 (Bangalore)
				new Location(8, 19.182157, 84.791682),  // Customer 7 (Bhubaneswar)
				new Location(9, 31.5497, 74.3436),      // Customer 8 (Lahore)
				new Location(10, 22.719568, 75.857727), // Customer 9 (Indore)
				new Location(11, 26.8467, 80.9462),     // Customer 10 (Lucknow)
				new Location(12, 28.704060, 77.102493), // Customer 11 (Delhi)
				new Location(13, 21.1702, 72.8311),     // Customer 12 (Surat)
				new Location(14, 15.3173, 75.7139),     // Customer 13 (Hubli)
				new Location(15, 10.8505, 76.2711)      // Customer 14 (Mysore)
		);

		// TODO: calculate total distance to reach to another locations
		locations.forEach(location -> location.calculateDistances(locations));


		// vehicle data
		List<Vehicle> vehicles = List.of(
				new Vehicle(1, 900, locations.get(0), LocalDateTime.of(2023, 12, 11, 8, 0)),
				new Vehicle(2, 120, locations.get(0), LocalDateTime.of(2023, 12, 11, 8, 0))
//				new Vehicle(3, 300, locations.get(0), LocalDateTime.of(2023, 12, 11, 8, 0))
		);

		// visits data

			List<Visit> visits = List.of(
			     	new Visit(1, "Gautam", locations.get(1), 200,LocalDateTime.of(2022, 12, 11, 9, 0), LocalDateTime.of(2022, 12, 11, 12, 0), Duration.ofMinutes(30)),
					new Visit(2, "Aman", locations.get(2), 120, LocalDateTime.of(2023, 12, 11, 10, 0), LocalDateTime.of(2023, 12, 11, 13, 0), Duration.ofMinutes(20)),
					new Visit(3, "Himanshu", locations.get(3), 300, LocalDateTime.of(2023, 12, 11, 10, 0), LocalDateTime.of(2023, 12, 11, 13, 0), Duration.ofMinutes(20)),
					new Visit(4, "Abhay", locations.get(4), 400, LocalDateTime.of(2023, 12, 11, 10, 0), LocalDateTime.of(2023, 12, 11, 13, 0), Duration.ofMinutes(20)),
					new Visit(5, "Abhinav", locations.get(5), 90, LocalDateTime.of(2023, 12, 11, 10, 0), LocalDateTime.of(2023, 12, 11, 13, 0), Duration.ofMinutes(20)),
					new Visit(6, "Ashutosh", locations.get(6), 90, LocalDateTime.of(2023, 12, 11, 10, 0), LocalDateTime.of(2023, 12, 11, 13, 0), Duration.ofMinutes(20)),
					new Visit(7, "Anurag", locations.get(7), 90, LocalDateTime.of(2023, 12, 11, 10, 0), LocalDateTime.of(2023, 12, 11, 13, 0), Duration.ofMinutes(20)),
					new Visit(8, "Ankit", locations.get(8), 90, LocalDateTime.of(2023, 12, 11, 10, 0), LocalDateTime.of(2023, 12, 11, 13, 0), Duration.ofMinutes(20)),
					new Visit(9, "Sandeep", locations.get(9), 90, LocalDateTime.of(2023, 12, 11, 10, 0), LocalDateTime.of(2023, 12, 11, 13, 0), Duration.ofMinutes(20)),
					new Visit(10, "Ashwani", locations.get(10), 90, LocalDateTime.of(2023, 12, 11, 10, 0), LocalDateTime.of(2023, 12, 11, 13, 0), Duration.ofMinutes(20))

		);

		return new VehicleSolution(1,visits,vehicles);
	}


	private static void printSolution(VehicleSolution solution) {

		if (solution == null) {
			System.out.println("No solution found.");
			return;
		}

		for (Vehicle vehicle : solution.getVehicles()) {

			System.out.println("Vehicle " + vehicle.getId() + " Route: ");
			if (vehicle.getRoute() == null || vehicle.getRoute().isEmpty()) {
				System.out.println("No route assigned to this vehicle.");
			} else {
				vehicle.getRoute().forEach(location ->
						System.out.println("Location ID: " + location.getId() + ", Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude())
				);
			}

			System.out.println("Vehicle Capacity: " + vehicle.getCapacity());
			System.out.println("Total Demand: " + vehicle.getTotalDemand());
			System.out.println("Total Distance: " + vehicle.getTotalDistanceMeters() + " meters\n");
		}


//		long totalDistance = solution.getDistanceMeters();
//		System.out.println("Total Distance of all vehicles: " + totalDistance + " meters");
		System.out.println("Score : " + solution.getHardSoftScore());
	}

}
