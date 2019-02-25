package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {

		// create session factory 
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).
				addAnnotatedClass(InstructorDetail.class).
				addAnnotatedClass(Course.class).
				buildSessionFactory();
		
		
		// create session 
		Session session = factory.getCurrentSession();
		
		try {
			
			// create a student object 
			System.out.println("Creating a new student object.");
			
			Instructor tempInstructor = new Instructor("Susan", "Public", "susan.public@luv2code.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "video games");
			
			// associate 
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start a transaction 
			session.beginTransaction();

			// this will ALSO save the details object because of CascadeType.ALL
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);
			
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
