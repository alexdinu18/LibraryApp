/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entity.LibraryUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mdinu
 */
@Stateless
public class LibraryUserFacade extends AbstractFacade<LibraryUser> {

    @PersistenceContext(unitName = "jpa-sql-tutorialPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LibraryUserFacade() {
        super(LibraryUser.class);
    }
    
}
