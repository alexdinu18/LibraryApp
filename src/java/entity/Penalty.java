/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author mdinu
 */
@Entity
@Table(name="PENALTY")
public class Penalty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="USERS_ID")
    private LibraryUser user;

    @ManyToOne
    @JoinColumn(name = "BOOKS_ID")
    private Book book;
    
    @Column(name="PENALTY_PRICE")
    private Integer penaltyPrice;

    public Penalty() {
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LibraryUser getUser() {
        return user;
    }

    public void setUser(LibraryUser user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getPenaltyPrice() {
        return penaltyPrice;
    }

    public void setPenaltyPrice(Integer penaltyPrice) {
        this.penaltyPrice = penaltyPrice;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Penalty)) {
            return false;
        }
        Penalty other = (Penalty) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Penalty[ id=" + id + " ]";
    }
    
}
