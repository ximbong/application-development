/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Database.Department;
import Database.Message;
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
@Path("msg")
public class MessageFacadeREST extends AbstractFacade<Message> {

    @PersistenceContext(unitName = "WebApplicationPU")
    private EntityManager em;

    public MessageFacadeREST() {
        super(Message.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(@QueryParam("dpm_id") int id1,@QueryParam("sender_id") int id2, @QueryParam("content") String content,@QueryParam("description") String description, @QueryParam("details") String details, @QueryParam("place") String place) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<Users> q = cb.createQuery(Users.class);
        Root<Users> c = q.from(Users.class);
        q.select(c);
 
        q.where(  
                cb.equal(c.get("id"), id2)
        );
        TypedQuery<Users> query = em.createQuery(q);
        Users newUs= query.getSingleResult();
        
           
        CriteriaQuery<Department> q2 = cb.createQuery(Department.class);
        Root<Department> c2 = q2.from(Department.class);
        q2.select(c2);
  
        q2.where(  
                cb.equal(c2.get("id"), id1)
        );
        TypedQuery<Department> query2 = em.createQuery(q2);
        Department newDp= query2.getSingleResult();
        
        Message newMs = new Message();
        if ( content == null) {
            newMs.setIsTask(true);
} else {
             newMs.setIsTask(false);
        }
        newMs.setDepartmentId(newDp);
        newMs.setContent(content);
        newMs.setSenderId(newUs);
        newMs.setDescription(description);
        newMs.setDetails(details);
        newMs.setPlace(place);
        super.create(newMs);
        
    }
    
   
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Message entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Message find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Message> findAll(@QueryParam("id") int id) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Department> q = cb.createQuery(Department.class);
        Root<Department> c = q.from(Department.class);
        q.select(c);
  
        q.where(    
            cb.equal(c.get("id"), id)
        );
        
        TypedQuery<Department> query = em.createQuery(q);
        
        Department newDp = query.getSingleResult();
        //System.out.println(newDp.getId());
        CriteriaQuery<Message> q2 = cb.createQuery(Message.class);
        Root<Message> c2 = q2.from(Message.class);
         
        q2.select(c2);
        q2.where(    
            cb.equal(c2.get("departmentId"), newDp)
       );
        
        TypedQuery<Message> query2 = em.createQuery(q2);
        
       
        return query2.getResultList();
    }
    

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Message> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
