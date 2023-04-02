package com.example.carRental.controller;

import com.example.carRental.model.Car;
import com.example.carRental.repository.CarRepository;
import com.example.carRental.repository.UserRepository;
import com.example.carRental.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Configuration
@RestController
public class CarController {
    @Autowired
    CarRepository carRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CarService carService;
//@RequestMapping("/cars")
    @GetMapping("/cars")
    public ResponseEntity getCars() {
        List<Car> cars = carRepository.findAll();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/cars/sortCars")
    public ResponseEntity sortCars(@RequestParam String direction, @RequestParam String sortingMethod) {
        return ResponseEntity.ok(carService.sortCars(direction, sortingMethod));
    }


 /*   @GetMapping("/cars/filterCars")

    }*/

    @PutMapping("/cars/carRent")
    public ResponseEntity rentCar(@RequestParam Integer carId, @RequestParam  Long userId) {
        return carService.rentCar(carId, userId );
    }

    @PutMapping("/cars/carReturn")
    public ResponseEntity returnCar(@RequestParam Long userId, @RequestParam Integer carId) {
        return carService.returnCar(userId, carId);

    }
   // @RequestMapping("/admin")
    @PostMapping("admin/addCar")
    public ResponseEntity addCar(@RequestBody Car car) {
        return ResponseEntity.ok(carRepository.save(car));
    }

    @DeleteMapping("/admin/deleteCar/{carId}")
    public ResponseEntity deleteCar(@PathVariable Integer carId) {
        return carService.deleteCar(carId);
    }
}
