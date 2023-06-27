package com.vishwa.Hibernate.Mappings.dao;

import com.vishwa.Hibernate.Mappings.entity.Course;
import com.vishwa.Hibernate.Mappings.entity.Instructor;
import com.vishwa.Hibernate.Mappings.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDaoImpl implements AppDao{

    private EntityManager entityManager;

    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    @Transactional
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);
        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCourseWithInstructorId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);

        List<Course> getCourses = query.getResultList();

        return getCourses;
    }

    @Override
    public Instructor findInstructorByJoinFetch(int id) {
        TypedQuery<Instructor> instructor = entityManager.createQuery(
                "select i from Instructor i " + "JOIN FETCH i.courses " +
                        "where i.id = :data ", Instructor.class
        );

        instructor.setParameter("data", id);
        Instructor instructors = instructor.getSingleResult();
        return instructors;
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorCoursesById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);

        List<Course> courses = instructor.getCourses();

        for(Course course: courses) {
            course.setInstructor(null);
        }

        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteCoursebyId(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourse(int id) {
        TypedQuery<Course> courseTypedQuery = entityManager.createQuery(
                "select c from Course c "
                + " JOIN FETCH c.reviews "
                + "where c.id = :data", Course.class
        );
        courseTypedQuery.setParameter("data",10);
        Course course = courseTypedQuery.getSingleResult();
        return course;
    }
}
