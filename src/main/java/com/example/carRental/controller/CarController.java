package com.example.carRental.controller;

import com.example.carRental.model.Car;
import com.example.carRental.model.User;
import com.example.carRental.repository.CarRepository;
import com.example.carRental.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @GetMapping
    public ResponseEntity getCars() {
        List<Car> cars = carRepository.findAll();
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/sortByASC")
    public ResponseEntity carsByASC(@RequestParam String sortingMethod) {
        List<Car> cars = carRepository.findAll(Sort.by(Sort.Direction.ASC, sortingMethod));
        return ResponseEntity.ok(cars);
    }
    @GetMapping("/sortByDSC")
    public ResponseEntity carsByDSC(@RequestParam String sortingMethod) {
        List<Car> cars = carRepository.findAll(Sort.by(Sort.Direction.DESC, sortingMethod));
        return ResponseEntity.ok(cars);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car) {
        Car savedCar = carRepository.save(car);
        return ResponseEntity.ok(savedCar);
    }

    @PutMapping("/{userId}/carRent")
    public ResponseEntity rentCar(@PathVariable Long userId, @RequestParam Integer carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new NoSuchElementException());
        if (car.isAvailability() == true) {
            User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
            user.getCarsList().add(car);
            car.setAvailability(false);
            return ResponseEntity.ok(userRepository.save(user));
        } else return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Car isn't available");
    }
    @PutMapping("/{userId}/carReturn")
    public ResponseEntity returnCar(@PathVariable Long userId, @RequestParam Integer carId){
        Car car = carRepository.findById(carId).orElseThrow(() -> new NoSuchElementException());
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        if (car.isAvailability() == false ) {
            user.getCarsList().remove(car);
            car.setAvailability(true);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).build();
        }else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
}
