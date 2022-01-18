package com.hassan.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.hassan.entity.Employee;

public class CreateEmployee {

   public void createEmployee(String name, int salary, String degree ) {
   
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPATesting" );
      
      EntityManager entitymanager = emfactory.createEntityManager( );
      entitymanager.getTransaction( ).begin( );

      Employee employee = new Employee( ); 
      employee.setEname( name );
      employee.setSalary( salary );
      employee.setDeg( degree );
      
      entitymanager.persist( employee );
      entitymanager.getTransaction( ).commit( );

      entitymanager.close( );
      emfactory.close( );
   }
}
