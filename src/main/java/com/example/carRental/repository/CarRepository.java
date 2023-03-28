package com.example.carRental.repository;

import com.example.carRental.model.Car;
import com.example.carRental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByManufacturer(String manufacturer);

    List<Car> findByModel(String model);

    List<Car> findByPriceGreaterThanEqual(Double price);
    //List<Car> findByModel(String model);



}
