package com.fleet.vehicle_routing.service;


import com.graphhopper.GraphHopper;
import com.graphhopper.config.Profile;
import org.springframework.stereotype.Service;

@Service
public class GraphHopperService {
     GraphHopper graphHopper;

    // initalization of  graphhoper

    public GraphHopperService(){
        String graphHopperFolder = "graph-cache";
        String osmFile = getClass().getResource("/central-zone-india.osm.pbf").getPath();

        // Initialize GraphHopper
        graphHopper = new GraphHopper();
        graphHopper.setOSMFile(osmFile);
        graphHopper.setGraphHopperLocation(graphHopperFolder);

//        // Add profiles for routing (e.g., car, bike, foot)
//        graphHopper.set(EncodingManager.create("car"));
        graphHopper.setProfiles(
                new Profile("car").setVehicle("car").setWeighting("fastest")
        );

        // Build the graph
        try {
//            graphHopper.clean();
            graphHopper.importOrLoad();
        } catch (Exception e) {
            throw new IllegalStateException("Error initializing GraphHopper", e);
        }
    }

    public GraphHopper getGraphHopper() {
        return graphHopper;
    }

}
