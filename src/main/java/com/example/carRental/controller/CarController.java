package com.example.carRental.controller;

import com.example.carRental.model.Car;
import com.example.carRental.repository.CarRepository;
import com.example.carRental.repository.UserRepository;
import com.example.carRental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Configuration
@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    CarRepository carRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity getCars() {
        List<Car> cars = carRepository.findAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/sortCars")
    public ResponseEntity sortCars(@RequestParam String direction, @RequestParam String sortingMethod) {
        return ResponseEntity.ok(carService.sortCars(direction, sortingMethod));
    }


 /*   @GetMapping("/cars/filterCars")

    }*/

    @PutMapping("/carRent")
    public ResponseEntity rentCar(@RequestParam Integer carId, @RequestParam  Long userId) {
        carService.rentCar(carId, userId );
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/carReturn")
    public ResponseEntity returnCar(@RequestParam Long userId, @RequestParam Integer carId) {
         carService.returnCar(userId, carId);
         return ResponseEntity.noContent().build();

    }
    @PostMapping("/addCar")
    public ResponseEntity addCar(@RequestBody Car car) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carRepository.save(car));
    }

    @DeleteMapping("/deleteCar/{carId}")
    public ResponseEntity deleteCar(@PathVariable Integer carId) {
        carService.deleteCar(carId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
