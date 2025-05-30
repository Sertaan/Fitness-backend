/*package com.gymbooking.fitness;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FitnessController {

    @Autowired
    private GymClassRepository gymClassRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // Hämta alla gympass
    @GetMapping("/gymclasses")
    public List<GymClass> getAllGymClasses() {
        return gymClassRepository.findAll();
    }

    // Skapa nytt gympass
    @PostMapping("/gymclasses")
    public GymClass createGymClass(@RequestBody GymClass gymClass) {
        return gymClassRepository.save(gymClass);
    }

    // Boka ett gympass
    @PostMapping("/bookings")
    public ResponseEntity<String> bookGymClass(@RequestBody Booking booking) {
        // Kolla att gympasset finns
        GymClass gymClass = gymClassRepository.findById(booking.getGymClass().getId()).orElse(null);
        if (gymClass == null) {
            return ResponseEntity.badRequest().body("Gympasset finns inte.");
        }

        // Kontrollera max antal bokningar
        int currentBookings = bookingRepository.findByGymClassId(gymClass.getId()).size();
        if (currentBookings >= gymClass.getMaxParticipants()) {
            return ResponseEntity.badRequest().body("Gympasset är fullbokat.");
        }

        bookingRepository.save(booking);
        return ResponseEntity.ok("Bokningen är gjord!");
    }

    // Hämta bokningar för en användare
    @GetMapping("/bookings/{username}")
    public List<Booking> getBookingsForUser(@PathVariable String username) {
        return bookingRepository.findByUsername(username);
    }
}
*/