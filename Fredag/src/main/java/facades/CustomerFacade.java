package facades;

import DTO.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CustomerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public CustomerFacade(EntityManagerFactory emf) {
         this.emf=emf; 
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
    public long getRenameMeCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }
    
    public List<BankCustomer> getAllCustomer () {
        EntityManager em = emf.createEntityManager(); 
        try {
            TypedQuery<BankCustomer> query = em.createQuery("Select c from BankCustomer c", BankCustomer.class); 
            return query.getResultList(); 
        }finally {
            em.close();
        }
    }
    public BankCustomer addCustomer (BankCustomer c) {
        EntityManager em = emf.createEntityManager(); 
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c; 
        }finally {
            em.close();
        }
    }
    public CustomerDTO getCustomerById (int id) {
        EntityManager em = emf.createEntityManager(); 
        try {
            BankCustomer c = em.find(BankCustomer.class, (long)id); 
            CustomerDTO ctdo = new CustomerDTO(c); 
            return ctdo; 
        } finally {
        em.close();
    }
    }
    
    public List<CustomerDTO> findCustByName (String name) {
        EntityManager em = emf.createEntityManager(); 
        List<CustomerDTO> custlist = new ArrayList<CustomerDTO>();
        try {
        TypedQuery<BankCustomer> query = em.createQuery("SELECT c FROM BankCustomer c WHERE c.firstName = :firstName", BankCustomer.class);
          query.setParameter("firstName", name);
          for(BankCustomer bc: query.getResultList()){
            custlist.add(new CustomerDTO (bc)); 
          }
          return custlist; 
        } finally {
            em.close();
        }
    }

}
