package com.org.spatial.citiesnearme.service;

import com.org.spatial.citiesnearme.model.City;
import com.org.spatial.citiesnearme.model.CityLocation;
import com.org.spatial.citiesnearme.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CityService {


    @Autowired
    CityRepository cityRepository;

    public List<City> findAllCities() {
        return cityRepository.findAll();
    }

    public List<City> findCitiesNearLocation(Point point, Distance distanceKm) {

        return cityRepository.findByLocationNear(point, distanceKm);
    }


    public void addCities(String cityName, CityLocation location) {

        final GeoJsonPoint locationPoint = new GeoJsonPoint(
                Double.valueOf(location.getLongitude()),
                Double.valueOf(location.getLatitude()));

        cityRepository.save(new City(cityName, locationPoint));

    }

    public CityLocation findLocation(String cityName) {
        CityLocation location = new CityLocation();
        GeoJsonPoint point = cityRepository.findByCityName(cityName).get(0).getLocation();
        location.setLongitude(point.getX());
        location.setLatitude(point.getY());
        return location;
    }
}
