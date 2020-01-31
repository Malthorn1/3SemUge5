/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import facades.CustomerFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author mikke
 */
public class MakeTestData {

    public static void main(String[] args) {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        CustomerFacade facade = new CustomerFacade(emf);

        
        try {
            em.getTransaction().begin(); 
        BankCustomer b1 = new BankCustomer("Mads", "Larsen", "222000222", 200, 5, "Internal info1");
        BankCustomer b2 = new BankCustomer("Rasmus", "Madsen", "111000333", 10000, 2, "Internal info2");
        em.persist(b1);
        em.persist(b2);
        em.getTransaction().commit();
        
        } finally {
            em.close();
        }        }
    }


