/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entity.Request;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mdinu
 */
@Stateless
public class RequestFacade extends AbstractFacade<Request> {

    @PersistenceContext(unitName = "jpa-sql-tutorialPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RequestFacade() {
        super(Request.class);
    }
    
}
