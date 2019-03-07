package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {

		// create session factory 
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).
				addAnnotatedClass(InstructorDetail.class).
				addAnnotatedClass(Course.class).
				addAnnotatedClass(Review.class).
				addAnnotatedClass(Student.class).
				buildSessionFactory();
		
		
		// create session 
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();

			// create a course 
			Course tempCourse = new Course("Pacman - How to Score One Million Points");
			System.out.println("Created new course: " + tempCourse);
			
			// create students
			Student tempStudent1 = new Student("Mary", "Poppins", "mpoppins@nomail.com"); 
			Student tempStudent2 = new Student("George", "Tolkien", "gtolly@nomail.com"); 


			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);

			// save the students 
			System.out.println("\nSaving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved stduents: " + tempCourse.getStudents());
			
//			session.save(tempCourse);
			
			// commit transaction 
			session.getTransaction().commit();
			
		} 
		finally { 
			
			// add clean up code
			session.close(); 
			
			factory.close();
		}
		
	}

}
