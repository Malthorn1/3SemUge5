package rest;

import DTO.CustomerDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.BankCustomer;
import utils.EMF_Creator;
import facades.CustomerFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("bankcustomer")
public class BankCustomerResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/3101fredagsopgave",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final CustomerFacade FACADE =  CustomerFacade.getCustomerFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getRenameMeCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getBankCustomers() {
        List<BankCustomer> allCustomers = FACADE.getAllCustomer(); 
        return new Gson().toJson(allCustomers); 
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomerFromId (@PathParam("id") int id) {
        CustomerDTO cust = FACADE.getCustomerById(id); 
        return GSON.toJson(cust); 
    }
    
    @GET
    @Path ("name/{name}") 
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomerFromName (@PathParam("name") String name) {
        List<CustomerDTO> cust = FACADE.findCustByName(name); 
        return GSON.toJson(cust); 
    }
    
    

 
}
