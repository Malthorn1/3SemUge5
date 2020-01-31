package rest.service;

import DTO.EmployeeDTO;
import com.google.gson.Gson;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("Employee")
public class EmployeeResource {
        
Gson gson = new Gson(); 
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityManager em = emf.createEntityManager();
    EmployeeFacade facade = new EmployeeFacade(emf);
    

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }
    
     @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        throw new UnsupportedOperationException();
    }

    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEmployees() {
        List<Employee> allEmployees = facade.getAllEmployees();
        ArrayList<EmployeeDTO> allDTO = new ArrayList<>() ; 
         for(Employee employee : allEmployees) {
             allDTO.add(new EmployeeDTO(employee)); 
    }
         return gson.toJson(allDTO); 
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeFromId (@PathParam("id") int id) {
        Employee emp = facade.findEmployeeById(id); 
        EmployeeDTO DTO = new EmployeeDTO(emp) ; 
        return gson.toJson(DTO); 
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeName(@PathParam("name") String name) {
        List<Employee> specificEmployees = facade.findEmployeeByName(name);
        ArrayList<EmployeeDTO> allDTO = new ArrayList<>() ; 
         for(Employee employee : specificEmployees) {
             allDTO.add(new EmployeeDTO(employee)); 
         
         }
         return gson.toJson(allDTO); 
    }
             
    @GET
    @Path("money")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighestPaid() {
        List<Employee> mostmoney = facade.getEmployeesWithHighestSalary();
        List<EmployeeDTO> allDTO = new ArrayList();
        for(Employee employee: mostmoney){
            allDTO.add(new EmployeeDTO (employee));
                }
        return new Gson().toJson(allDTO);
    } }
            

