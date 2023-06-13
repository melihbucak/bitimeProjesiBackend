package com.bitirme.BitirmeProjesi.service;

import com.bitirme.BitirmeProjesi.entity.Location;
import com.bitirme.BitirmeProjesi.repo.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;


    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public boolean deleteLocation(Long id)
    {
        try {
            locationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public List<Location> getLocations() {
        return locationRepository.findAll();
    }

}
