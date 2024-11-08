package com.example.restaurantreservationaa.controller;

import com.example.restaurantreservationaa.domain.Reservation;
import com.example.restaurantreservationaa.exception.ReservationNotFoundException;
import com.example.restaurantreservationaa.repository.ReservationRepository;
import com.example.restaurantreservationaa.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository passengerRepository;

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> getAll() {
        return new ResponseEntity<>(reservationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/reservations/:reservationId")
    public ResponseEntity<Reservation> getReservation(long reservationId)  throws ReservationNotFoundException {
        Reservation reservation = reservationService.get(reservationId);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }


    @PostMapping("/reservations")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        return new ResponseEntity<>(reservationService.add(reservation), HttpStatus.CREATED);
    }

    @DeleteMapping("/reservation/:reservationId")
    public ResponseEntity<Void> removeReservation(long reservationId) throws ReservationNotFoundException{
        reservationService.remove(reservationId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ResponseEntity<Void> handleReservationNotFoundException(ReservationNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}