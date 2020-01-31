package facades;

import DTO.CustomerDTO;
import utils.EMF_Creator;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class CustomerFacadeTest {
    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
    private static final CustomerFacade facade = CustomerFacade.getCustomerFacade(ENF);


    public CustomerFacadeTest() {
    }



    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */

   // @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
  // @BeforeEach
    public void setUp() {
        EntityManager em = ENF.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("RenameMe.deleteAllRows").executeUpdate();
           em.persist(new BankCustomer("Mads", "Larsen", "22200222", 0, 0, "internal Info 1")); 
           em.persist(new BankCustomer("Rasmus", "Klausen", "111222333", 0, 0, "internal info 2")); 

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

  //  @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
 //   @Test
    public void testAFacadeMethod() {
        assertEquals(2, facade.getRenameMeCount(), "Expects two rows in the database");
    }
    
    @Test
    public void testGetCustomerByID () {
        CustomerDTO b1 = facade.getCustomerById(1); 
        
        assertEquals(b1.getFullName(), "Mads Larsen");
    }
    
    @Test
    public void testGetCustomerByName () {
        List<CustomerDTO> b1 = new ArrayList(facade.findCustByName("Mads")); 
        
        assertEquals(b1.get(0).getCustomerID(), 1);
    }
    
     @Test
    public void testGetAllEmployee() {
        List<BankCustomer> b1 = new ArrayList(facade.getAllCustomer());
        
        //Asserting size, should be 2
        assertEquals(b1.size(), 2);
    }

}
