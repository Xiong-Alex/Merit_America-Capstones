package com.techelevator.service;

import java.util.List;

import com.techelevator.model.ApproveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techelevator.dao.RestaurantDao;
import com.techelevator.model.Restaurant;

@Service
public class RestaurantService {
    @Autowired
    RestaurantDao restaurantDao;

    public RestaurantService(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    public List<Restaurant> getRestaurant(String city, String zip, String type) {
        List<Restaurant> restaurants;

        if (city == null && zip == null && type == null) {
            restaurants = restaurantDao.findAll();
        } else {
            restaurants = restaurantDao.findBy(
                    city != null ? city : "",
                    zip != null ? zip : "",
                    type != null ? type : "");
        }
        
        return restaurants;
    }
}