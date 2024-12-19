package com.fleet.vehicle_routing.controller;

import com.fleet.vehicle_routing.service.RoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/route")
public class RouteController {
    @Autowired
   private RoutingService routingService;

    @GetMapping
    public ResponseEntity<Object> getRoute(
            @RequestParam double latFrom,
            @RequestParam double lonFrom,
            @RequestParam double latTo,
            @RequestParam double lonTo) {
        try{
            return ResponseEntity.ok().body(routingService.calculateRoute(latFrom, lonFrom, latTo, lonTo));
        } catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
