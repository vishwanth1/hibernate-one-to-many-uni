package com.vishwa.Hibernate.Mappings;

import com.vishwa.Hibernate.Mappings.dao.AppDao;
import com.vishwa.Hibernate.Mappings.entity.Course;
import com.vishwa.Hibernate.Mappings.entity.Instructor;
import com.vishwa.Hibernate.Mappings.entity.InstructorDetail;
import com.vishwa.Hibernate.Mappings.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class HibernateMappingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateMappingsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao) {
		return runner -> {
			// createCourseWithReviews(appDao);
			// findCourseWithReviews(appDao);
			deleteCourseAndReviews(appDao);
		};
	}

	private void deleteCourseAndReviews(AppDao appDao) {
		appDao.deleteCoursebyId(10);
	}

	private void findCourseWithReviews(AppDao appDao) {
		Course course = appDao.findCourse(10);
		System.out.println(course.getReviews());
		System.out.println(course);
	}

	private void createCourseWithReviews(AppDao appDao) {

		Course course = new Course("Javascript");
		Review review1 = new Review("Excellent");
		Review review2 = new Review("Good");
		course.addReviews(review1);
		course.addReviews(review2);
		appDao.save(course);
	}

	private void deleteCourseById(AppDao appDao) {
		appDao.deleteCoursebyId(14);
	}

	private void deleteInstructorCourses(AppDao appDao) {
		appDao.deleteInstructorCoursesById(1);
	}

	private void updateCourse(AppDao appDao) {

		Course courses = appDao.findCourseById(11);
		courses.setTitle("Python Language");
		appDao.updateCourse(courses);
	}

	private void updateInstructor(AppDao appDao) {

		Instructor tempInstructor = appDao.findInstructorById(1);

		tempInstructor.setLastName("Tester");

		appDao.updateInstructor(tempInstructor);
	}

	private void findInstructorWithFetch(AppDao appDao) {
		Instructor instructor = appDao.findInstructorByJoinFetch(1);
		System.out.println(instructor);
		System.out.println(instructor.getCourses());
		System.out.println(instructor.getInstructorDetail());
	}

	private void findCoursesForInstructor(AppDao appDao) {
		Instructor instructor = appDao.findInstructorById(1);
		List<Course> courses = appDao.findCourseWithInstructorId(instructor.getId());
		System.out.println(instructor);
		instructor.setCourses(courses);
		System.out.println(instructor.getCourses());
	}

	private void createInstructorWithCourses(AppDao appDao) {
		Instructor instructor = new Instructor("Sai", "N", "sai@gmail.com");

		InstructorDetail instructorDetail = new InstructorDetail("sai.com", "Developer");

		instructor.setInstructorDetail(instructorDetail);

		Course course = new Course("C language");
		Course course1 = new Course("Java Language");

		instructor.add(course);
		instructor.add(course1);

		appDao.save(instructor);

	}

	private void deleteInstructorDetail(AppDao appDao) {
		appDao.deleteInstructorDetailById(8);
		System.out.println("Deleted Successfully");
	}

	private void findInstructorDetail(AppDao appDao) {
		InstructorDetail instructorDetail = appDao.findInstructorDetailById(1);
		System.out.println(instructorDetail);
		System.out.println(instructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDao appDao) {
		appDao.deleteInstructorById(2);
		System.out.println("Deleted Successfully");
	}

	private void findInstructor(AppDao appDao) {
		Instructor instructor =  appDao.findInstructorById(1);
		System.out.println(instructor);
		System.out.println(instructor.getInstructorDetail());
	}

	private void createInstructor(AppDao appDao) {
		Instructor instructor = new Instructor("Rahul", "G", "sample1@gmail.com");

		InstructorDetail instructorDetail = new InstructorDetail("sample1.com", "Business");

		instructor.setInstructorDetail(instructorDetail);

		appDao.save(instructor);
	}

}
