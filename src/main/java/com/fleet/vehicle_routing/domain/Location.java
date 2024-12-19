package com.fleet.vehicle_routing.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Location {
    private int id;
    private double latitude;
    private double longitude;

    private Map<Location, Long> drivingTimeSeconds = new HashMap<>();

    public Location(){

    }
    public Location(int id, double latitude, double longitude){
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }


    public Map<Location, Long> getDrivingTimeSeconds() {
        return drivingTimeSeconds;
    }

    public void setDrivingTimeSeconds(Map<Location, Long> drivingTimeSeconds) {
        this.drivingTimeSeconds = drivingTimeSeconds;
    }

    public long getDrivingTimeTo(Location location) {
        return drivingTimeSeconds.get(location);
    }

    public void calculateDistances(List<Location> locations) {
        for (Location location : locations) {
            long distance = (long) Math.sqrt(Math.pow(latitude - location.latitude, 2) + Math.pow(longitude - location.longitude, 2));
            drivingTimeSeconds.put(location, distance);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
