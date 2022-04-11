package com.org.spatial.citiesnearme.repository;


import com.org.spatial.citiesnearme.model.City;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends MongoRepository<City, String> {

    List<City> findByLocationNear(Point point, Distance distance);

    List<City> findByCityName(String name);



}
