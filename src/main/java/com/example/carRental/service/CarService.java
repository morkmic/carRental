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

    public void rentCar(Integer carId, Long userId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new NoSuchElementException());
        if (car.isAvailability() == true) {
            User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
            user.getCarsList().add(car);
            car.setAvailability(false);
            userRepository.save(user);
        }
    }


    public void returnCar(Long userId, Integer carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new NoSuchElementException());
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        if (car.isAvailability() == false) {
            user.getCarsList().remove(car);
            car.setAvailability(true);
            userRepository.save(user);

        }
    }

    public List<Car> findByManufacturer(String manufacturer) {

        return carRepository.findByManufacturer(manufacturer);
    }

    public List<Car> findByModel(String model) {

        return carRepository.findByModel(model);
    }

    public List<Car> findByPriceGreaterThanEqual(Double price) {
        return carRepository.findByPriceGreaterThanEqual(price);
    }

    public void deleteCar(Integer carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new NoSuchElementException());
        carRepository.delete(car);
    }
}
