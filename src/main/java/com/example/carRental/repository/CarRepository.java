package com.example.carRental.repository;

import com.example.carRental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByManufacturer(String manufacturer);

    List<Car> findByModel(String model);
    List<Car> findByProductionYear(Integer productionYear);
    List<Car> findByHorsePowerGreaterThanEqual(double horsePower);
    List<Car> findByHorsePowerLessThanEqual(double horsePower);
    List<Car> findByPriceGreaterThanEqual(double price);
    List<Car> findByPriceLessThanEqual(double price);
    List<Car> findByAvailabilityTrue();
    List<Car> findByAvailabilityFalse();



}
