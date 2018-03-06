/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Database.Announcement;
import Database.Coversation;
import Database.Users;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
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
@Path("cv")
public class CoversationFacadeREST extends AbstractFacade<Coversation> {

    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;

    public CoversationFacadeREST() {
        super(Coversation.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Coversation entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Coversation entity) {
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
    public Coversation find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public int findAll(@QueryParam("id1") int id1,@QueryParam("id2") int id2) {
                 
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Users> q = cb.createQuery(Users.class);
        Root<Users> c = q.from(Users.class);
        
        q.select(c);
        q.where(    
            cb.equal(c.get("id"), id1)
        );
        TypedQuery<Users> query = em.createQuery(q);
        Users user1 = query.getSingleResult();
      
        CriteriaQuery<Users> q1 = cb.createQuery(Users.class);
        Root<Users> c1 = q1.from(Users.class);
        
        q1.select(c1);
        q1.where(    
            cb.equal(c1.get("id"), id2)
        );
        TypedQuery<Users> query1 = em.createQuery(q1);
        Users user2 = query1.getSingleResult();
        
        Collection <Coversation> cv1= user1.getCoversationCollection();
        Collection <Coversation> cv2= user2.getCoversationCollection();
        
        Coversation[] cv_array1 = cv1.toArray(new Coversation[cv1.size()]);
        Coversation[] cv_array2 = cv2.toArray(new Coversation[cv2.size()]);
       
         for(int j = 0; j < cv_array1.length; j++){
        System.out.println(cv_array1[j].getId());
         }
       
        for (Coversation cv_array11 : cv_array1) {
            for (Coversation cv_array21 : cv_array2) {
                if (cv_array11.getId()== cv_array21.getId()) {
                    return cv_array11.getId();
                } 
            }
        }

        
        return -1;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Coversation> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
