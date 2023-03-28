package com.example.carRental.controller;

import com.example.carRental.model.Car;
import com.example.carRental.repository.CarRepository;
import com.example.carRental.repository.UserRepository;
import com.example.carRental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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


 /*   @GetMapping("/filterCars")
    public ResponseEntity filterCars(@RequestParam(required = false) String manufacturer,
                                @RequestParam(required = false) String model,
                                @RequestParam(required = false) Double price) {
        return ResponseEntity.status();
    }*/

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car) {
        return ResponseEntity.ok(carRepository.save(car));
    }

    @PutMapping("/{userId}/carRent")
    public ResponseEntity rentCar(@PathVariable Long userId, @RequestParam Integer carId) {
        return carService.rentCar(userId, carId);
    }

    @PutMapping("/{userId}/carReturn")
    public ResponseEntity returnCar(@PathVariable Long userId, @RequestParam Integer carId) {
        return carService.returnCar(userId, carId);

    }

    @DeleteMapping("/{carId}")
    public ResponseEntity deleteCar(@PathVariable Integer carId) {
        return carService.deleteCar(carId);
    }
}
