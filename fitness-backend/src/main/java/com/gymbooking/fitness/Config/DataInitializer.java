package com.gymbooking.fitness.Config;


import com.gymbooking.fitness.Course.Course;
import com.gymbooking.fitness.Course.CourseService;
import com.gymbooking.fitness.DTO.CourseDTO;
import com.gymbooking.fitness.User.UserService;
import com.gymbooking.fitness.User.User;
import com.gymbooking.fitness.Course.Category;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    public ApplicationRunner initDatabase(CourseService courseService, UserService userService) {
        return args -> {
            // 🟢 Skapa en gym-kurs om det inte finns några kurser i databasen
            if (courseService.getAllCourses().isEmpty()) {
                Course gymCourse = new Course();
                gymCourse.setName("Vlad");
                gymCourse.setCode("VLAD101");
                gymCourse.setStartDate(LocalDate.now());
                gymCourse.setEndDate(LocalDate.of(2099, 12, 31));
                gymCourse.setCategory(Category.YOGA);
                gymCourse.setDescription("This is a gym course");

                // 🟢 Konvertera com.gymbooking.fitness.Course till CourseDTO innan vi skickar det
                CourseDTO gymCourseDTO = new CourseDTO(
                        null, // ID sätts av databasen
                        gymCourse.getName(),
                        gymCourse.getCode(),
                        gymCourse.getStartDate(),
                        gymCourse.getEndDate(),
                        gymCourse.getCategory(),
                        gymCourse.getDescription()
                );

                courseService.createOrUpdateCourse(gymCourseDTO);
                System.out.println("Gym course added!");
            } else {
                System.out.println("Database already contains courses");
            }

            if (userService.getAllUsers().isEmpty()) {
                User adminUser = new User();
                adminUser.setUsername("admin123");
                adminUser.setRole("ADMIN");
                adminUser.setFirstName("admin");
                adminUser.setLastName("admin");
                adminUser.setPassword("AdminPassword");
                adminUser.setEmail("admin@admin.com");
                userService.createOrUpdateUser(adminUser);
                System.out.println("Admin user added");

                User gymUser = new User();
                gymUser.setUsername("vlad123");
                gymUser.setRole("USER");
                gymUser.setFirstName("vla");
                gymUser.setLastName("vladd");
                gymUser.setPassword("VladPassword");
                gymUser.setEmail("vlad@vlad.com");
                userService.createOrUpdateUser(gymUser);
                System.out.println("Gym user added");
            } else {
                System.out.println("Database already contains users");
            }
        };
    }
}