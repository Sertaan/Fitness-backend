/*package com.gymbooking.fitness;

import com.gymbooking.fitness.Course.Course;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIgnoreProperties({"courses"})
public class GymClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private LocalDateTime dateTime;

    private int maxParticipants;

    @OneToMany(mappedBy = "gymClass")
    private List<Course> courses;

    // Konstruktorer
    public GymClass() {
    }

    public GymClass(Long id, String name, String description, LocalDateTime dateTime, int maxParticipants) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.maxParticipants = maxParticipants;
    }

    // Getters och Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public List<Course> getBookings() {
        return courses;
    }

    public void setBookings(List<Course> bookings) {
        this.courses = bookings;
    }


}
*/