package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    public EmployeeFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Employee findEmployeeById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee employee = em.find(Employee.class,(long)id);
            return employee;
        } finally {
            em.close();
        }
    }

    public List<Employee> findEmployeeByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT c FROM Employee c WHERE c.name = :name", Employee.class);
            query.setParameter("name", name);
            return query.getResultList(); 
        } finally {
            em.close();
        }
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT c from Employee c", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT c FROM Employee c WHERE c.salary = (SELECT MAX(c.salary) FROM Employee c) ", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Employee createEmployee(String name, String adress, double salary) {
        Employee employee = new Employee(name, adress, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction();
            return employee;
        } finally {
            em.close();
        }
    }

}
