package com.luv2code.hibernate.demo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class FetchJoinDemo {

	@SuppressWarnings("deprecation")
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
			session.beginTransaction();

			// option 2 for getting dependent entities during a lazy load: Hibernate query with HQL 
			
			// get instructor from db
			int theId = 1;
			
			Query<Instructor> query = session.createQuery("select i from Instructor i "
					+ "JOIN FETCH i.courses where i.id=:theInstructorId", 
					Instructor.class);
			
			query.setParameter("theInstructorId", theId);
			
			// execute query and get instructor
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("luv2code: Instructor: " + tempInstructor);
			
			// commit transaction 
			session.getTransaction().commit();
			
			// close session 
			session.close();
			
			System.out.println("luv2code: the session is now closed!");
			
			// get courses for instructor 
			System.out.println("luv2code: Courses: " + tempInstructor.getCourses());
		
			System.out.println("luv2code: Done!");
		} 
		finally { 
			
			// add clean up code
			session.close(); 
			
			factory.close();
		}
		
	}

}
