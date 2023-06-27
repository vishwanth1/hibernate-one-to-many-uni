package com.vishwa.Hibernate.Mappings.dao;

import com.vishwa.Hibernate.Mappings.entity.Course;
import com.vishwa.Hibernate.Mappings.entity.Instructor;
import com.vishwa.Hibernate.Mappings.entity.InstructorDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AppDao {

    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    List<Course> findCourseWithInstructorId(int id);

    Instructor findInstructorByJoinFetch(int id);

    void updateInstructor(Instructor instructor);

    void updateCourse(Course course);

    Course findCourseById(int id);

    void deleteInstructorCoursesById(int id);

    void deleteCoursebyId(int id);

    void save(Course course);

    Course findCourse(int id);
}
