package com.org.spatial.citiesnearme.controller;

import com.org.spatial.citiesnearme.model.City;
import com.org.spatial.citiesnearme.model.CityLocation;
import com.org.spatial.citiesnearme.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;


    @GetMapping("/all")
    public List<City> getAllCities() {
        return cityService.findAllCities();
    }

    @GetMapping()
    public List<City> getCitiesNearLocation(@RequestParam("latitude") double latitude,
                                @RequestParam("longitude") double longitude,
                                @RequestParam("distance") double distance) {

        Point point = new Point(Double.valueOf(longitude), Double.valueOf(latitude));
        Distance distanceKm = new Distance(distance, Metrics.KILOMETERS);

        return cityService.findCitiesNearLocation(point, distanceKm);
    }

    @GetMapping("/location")
    public CityLocation getLocation(@RequestParam(value = "cityName") String cityName) {
        return cityService.findLocation(cityName);
    }

    @PostMapping()
    public void addCity(@RequestParam("cityName") String cityName, @RequestBody CityLocation location) {
        cityService.addCities(cityName, location);
    }

}
