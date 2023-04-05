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

    @GetMapping("/filtration/manufacturer/{manufacturer}")
    public ResponseEntity findByManufacturer(@PathVariable String manufacturer) {
        return ResponseEntity.ok(carService.findByManufacturer(manufacturer));
    }

    @GetMapping("/filtration/model/{model}")
    public ResponseEntity findByModel(@PathVariable String model) {
        return ResponseEntity.ok(carService.findByModel(model));
    }

    @GetMapping("/filtration/productionYear/{productionYear}")
    public ResponseEntity findByProductionYear(@PathVariable Integer productionYear) {
        return ResponseEntity.ok(carService.findByProductionYear(productionYear));
    }

    @GetMapping("/filtration/horsePower/greater/{horsePower}")
    public ResponseEntity findByHorsePowerGreaterThanEqual(@PathVariable double horsePower) {
        return ResponseEntity.ok(carService.findByHorsePowerGreaterThanEqual(horsePower));
    }

    @GetMapping("/filtration/horsePower/less/{horsePower}")
    public ResponseEntity findByHorsePowerLessThanEqual(@PathVariable double horsePower) {
        return ResponseEntity.ok(carService.findByHorsePowerLessThanEqual(horsePower));
    }

    @GetMapping("/filtration/price/greater/{price}")
    public ResponseEntity findByPriceGreaterThanEqual(@PathVariable double price) {
        return ResponseEntity.ok(carService.findByPriceGreaterThanEqual(price));
    }

    @GetMapping("/filtration/price/less/{price}")
    public ResponseEntity findByPriceLessThanEqual(@PathVariable double price) {
        return ResponseEntity.ok(carService.findByPriceLessThanEqual(price));
    }

    @GetMapping("/filtration/availability/true")
    public ResponseEntity findByAvailabilityTrue() {
        return ResponseEntity.ok(carService.findByAvailabilityTrue());
    }

    @GetMapping("/filtration/availability/false")
    public ResponseEntity findByAvailabilityFalse() {
        return ResponseEntity.ok(carService.findByAvailabilityFalse());
    }

    @PutMapping("/carRent")
    public ResponseEntity rentCar(@RequestParam Integer carId, @RequestParam Long userId) {
        carService.rentCar(carId, userId);
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
