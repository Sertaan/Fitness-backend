/*package com.gymbooking.fitness.Config;

import com.gymbooking.fitness.Course.Course;
import com.gymbooking.fitness.Repository.CourseRepository;
import com.gymbooking.fitness.Repository.GymClassRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {


    private final CourseRepository courseRepository;

    public DataLoader(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) {
        if (CourseRepository.class() == 0) {
            courseRepository.save(new Course(null, "Yoga", "Mjuk och lugn yoga", LocalDateTime.parse("2025-06-01T10:00"), 10));
            courseRepository.save(new Course(null, "Spinning", "Högintensivt cykelpass", LocalDateTime.parse("2025-06-01T12:00"), 15));
            courseRepository.save(new Course(null, "HIIT", "Kondition och styrka", LocalDateTime.parse("2025-06-02T17:00"), 12));
        }
    }
}*/
