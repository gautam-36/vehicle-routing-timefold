package com.fleet.vehicle_routing.service;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.ResponsePath;
import com.graphhopper.util.shapes.GHPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoutingService {
    @Autowired
    private GraphHopperService graphHopperService;

    public Object calculateRoute(double latFrom, double lonFrom, double latTo, double lonTo) {
        GHPoint startPoint = new GHPoint(latFrom, lonFrom);
        GHPoint endPoint = new GHPoint(latTo, lonTo);

        GHRequest request = new GHRequest(startPoint, endPoint)
                .setProfile("car") // Use the profile set earlier
                .setLocale("en");

        GHResponse response = graphHopperService.getGraphHopper().route(request);

        if (response.hasErrors()) {
            throw new RuntimeException("Routing failed: " + response.getErrors());
        }

        ResponsePath path = response.getBest();

        return response.getBest();
//        return new RouteResult(
//                path.getDistance(), // Distance in meters
//                path.getTime() // Time in milliseconds
//        );
    }

    public static class RouteResult {
        private final double distance;
        private final long time;

        public RouteResult(double distance, long time) {
            this.distance = distance;
            this.time = time;
        }

        public double getDistance() {
            return distance;
        }

        public long getTime() {
            return time;
        }
    }

}
