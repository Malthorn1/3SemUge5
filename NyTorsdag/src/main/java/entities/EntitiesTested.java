/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import facades.EmployeeFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mikke
 */
public class EntitiesTested {
        
    public static void main(String[] args) {
    // Open a database connection
    // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        EmployeeFacade facade = new EmployeeFacade(emf);

        try {
            em.getTransaction().begin();
            Employee employee1 = new Employee("Aske", "Sydhavn", 2000);
            Employee employee2 = new Employee("Jens", "Skoven", 10);
            em.persist(employee1);
            em.persist(employee2);
            em.getTransaction().commit();
            System.out.println("Customer: " + facade.findEmployeeByName("Aske"));
    //        System.out.println(facade.getNumberOfCustomers());
            System.out.println(facade.getAllEmployees().size());
      //      System.out.println(facade.findCustomer(1));

        } finally {
            em.close();
        }

    }
    
}
