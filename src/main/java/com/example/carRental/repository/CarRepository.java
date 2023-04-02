package com.example.carRental.repository;

import com.example.carRental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByManufacturer(String manufacturer);

    List<Car> findByModel(String model);

    List<Car> findByPriceGreaterThanEqual(Double price);




}
