package com.example.carRental.controller;

import com.example.carRental.model.Car;
import com.example.carRental.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    CarRepository carRepository;
    @GetMapping
    public ResponseEntity getCars(){
        List<Car> cars = carRepository.findAll();
        return ResponseEntity.ok(cars.toString());
    }
    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car){
        Car savedCar = carRepository.save(car);
        return ResponseEntity.ok(savedCar);

    }
}
