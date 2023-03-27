package com.example.carRental.service;

import com.example.carRental.model.Car;
import com.example.carRental.model.User;
import com.example.carRental.repository.CarRepository;
import com.example.carRental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    CarRepository carRepository;

    public List<Car> sortCars(String direction, String sortingMethod) {
        List<Car> cars = null;
        if (direction.equalsIgnoreCase("DESC")) {
            cars = carRepository.findAll(Sort.by(Sort.Direction.DESC, sortingMethod));
        } else if (direction.equalsIgnoreCase("ASC")) {
            cars = carRepository.findAll(Sort.by(Sort.Direction.ASC, sortingMethod));
        }
        return cars;
    }

    public ResponseEntity rentCar(Long userId, Integer carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new NoSuchElementException());
        if (car.isAvailability() == true) {
            User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
            user.getCarsList().add(car);
            car.setAvailability(false);
            return ResponseEntity.ok(userRepository.save(user));
        } else return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Car isn't available");
    }


    public ResponseEntity returnCar( Long userId, Integer carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new NoSuchElementException());
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        if (car.isAvailability() == false) {
            user.getCarsList().remove(car);
            car.setAvailability(true);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
