package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {

		// create session factory 
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		
		
		// create session 
		Session session = factory.getCurrentSession();
		
		try {
			
			// start transaction 
			session.beginTransaction();

			// get instructor detail object 
			int theId = 3;
			
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			
			// print instructor detail
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
			
			// print associated instructor
			System.out.println("tempInstructorDetail's associated instructor: " + tempInstructorDetail.getInstructor());
			
			// NOW delete the instructor detail 
			System.out.println("Deleting tempInstructorDetail: " + tempInstructorDetail);
			
			// need to remove the associated object reference, so we need to: 
			// break the bi-directional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			// then delete the tempInstructorDetail
			session.delete(tempInstructorDetail);
			
			// commit transaction 
			session.getTransaction().commit();
			
		} catch ( Exception exc) {
			exc.printStackTrace();
		}
		finally { 
			session.close(); // solves the connection leak issue
			
			factory.close();
		}
		
	}

}
