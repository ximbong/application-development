/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Database.Department;
import Database.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author PLH
 */
@Stateless
@Path("users")
public class UsersFacadeREST extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "WebApplicationPU")
    private EntityManager em;

    public UsersFacadeREST() {
        super(Users.class);
    }
    
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Users> create(@QueryParam("username") String username, @QueryParam("password") String password) {
        System.out.println(username);
        System.out.println(password);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Users> q = cb.createQuery(Users.class);
        Root<Users> c = q.from(Users.class);
        q.select(c);
  
        q.where(
            cb.and(
                cb.equal(c.get("username"), username),
                cb.equal(c.get("password"), password)
            )
        );
        TypedQuery<Users> query = em.createQuery(q);
        return query.getResultList();
           
    }
    
    @Path("new")
     @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void createNew(@QueryParam("username") String username, @QueryParam("dpm_id") int id, @QueryParam("phone") String phone) {
         CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<Department> q2 = cb.createQuery(Department.class);
        Root<Department> c2 = q2.from(Department.class);
        q2.select(c2);
  
        q2.where(  
                cb.equal(c2.get("id"), id)
        );
        TypedQuery<Department> query2 = em.createQuery(q2);
        Department newDp= query2.getSingleResult();
        
        
        Users newUs = new Users();
        newUs.setUsername(username);
        newUs.setDepartmentId(newDp);
        newUs.setPassword("123");
        newUs.setPhonenumber(phone);
        newUs.setStatusCode(1);
        super.create(newUs);   
    }
    @PUT
    @Path("{id}/{status_code}")
     @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") int id, @PathParam("status_code") int status_code) {
             System.out.println(id);
        System.out.println(status_code);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Users> q = cb.createQuery(Users.class);
        Root<Users> c = q.from(Users.class);
        q.select(c);
  
        q.where(
                cb.equal(c.get("id"), id)
        );
        TypedQuery<Users> query = em.createQuery(q);
        Users user = query.getSingleResult();
        user.setStatusCode(status_code);
        
        super.edit(user);
        
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Users find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Users> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
