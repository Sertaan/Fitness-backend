package com.gymbooking.fitness.Repository;


import com.gymbooking.fitness.Course.Course;
import com.gymbooking.fitness.Course.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {  // 🔥 Se till att ID är Long

    Optional<Course> findCourseByName(String courseName);

    List<Course> findByCategory(Category category);

    @Query("SELECT c FROM Course c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Course> searchByName(@Param("name") String name);  // 🔥 Gör sökningen case-insensitive
}
