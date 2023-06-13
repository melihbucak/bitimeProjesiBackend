package com.bitirme.BitirmeProjesi.controller;

import com.bitirme.BitirmeProjesi.entity.Location;
import com.bitirme.BitirmeProjesi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping("/saveLocation")
    public ResponseEntity<Location> saveLocation(@RequestBody Location location) {
        Location location1 = locationService.saveLocation(location);
        return new ResponseEntity<>(location1, HttpStatus.CREATED);
    }

//    @GetMapping("/getLocation/{id}")
//    public Location getLocationById(@PathVariable("id") Long id) {
//        return locationService.getLocation(id);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteLocationById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(locationService.deleteLocation(id));
    }

    @GetMapping("/getLocations")
    public ResponseEntity<List<Location>> getLocations() {
        List<Location> locationList = locationService.getLocations();
        return ResponseEntity.ok(locationList);
    }
//    @PutMapping("/updateLocations")
//    public ResponseEntity updateStudent(@RequestBody LocationDto dto) {
//        return locationService.updateLocationFromDto(dto);
//    }
}
