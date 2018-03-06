/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Database.Coversation;
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

    @PersistenceContext(unitName = "WebApplication1PU")
    private EntityManager em;

    public MessageFacadeREST() {
        super(Message.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Message entity) {
        super.create(entity);
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
    public List<Message> findAll(@QueryParam("cv_id") int id) {
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Coversation> q = cb.createQuery(Coversation.class);
        Root<Coversation> c = q.from(Coversation.class);
        
        
        q.select(c);
        q.where(    
            cb.equal(c.get("id"), id)
        );
        
        TypedQuery<Coversation> query = em.createQuery(q);

        Coversation newCv = query.getSingleResult();

        CriteriaQuery<Message> q2 = cb.createQuery(Message.class);
        Root<Message> c2 = q2.from(Message.class);
         
        q2.select(c2);
//        q2.where(    
//            cb.equal(c2.get("coversationId"), newCv)
//        );
        
        TypedQuery<Message> query2 = em.createQuery(q2);
        List<Message> msg = query2.getResultList();
        
//        for(Message m : msg){
//            if(m.getCoversationId().getId() == id){
//                return m;
//            }
//        }
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
