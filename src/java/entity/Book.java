/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mdinu
 */
@Entity
@Table(name="BOOK")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    

    @NotNull
    @Column(name="TITLE")
    private String title;
    
    @NotNull
    @Column(name="AUTHOR")
    private String author;
    
    @NotNull
    @Column(name="PUBLISHER")
    private String publisher;
    
    @NotNull
    @Column(name="APPEARANCE_YEAR")
    private Integer appearanceYear;
    
    @NotNull
    @Column(name="NUMBER_OF_UNITS")
    private Integer numberOfUnits;
    
    @OneToMany(cascade = ALL, mappedBy = "book")
    private Collection<Penalty> penalties;
    
    @OneToMany(cascade = ALL, mappedBy = "book")
    private Collection<History> histories;
    
    @ManyToOne
    @JoinColumn(name="REQUESTS_ID")
    private Request request;

    @ManyToOne
    @JoinColumn(name="CARTS_ID")
    private Cart cart;

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getAppearanceYear() {
        return appearanceYear;
    }

    public void setAppearanceYear(Integer appearanceYear) {
        this.appearanceYear = appearanceYear;
    }

    public Integer getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(Integer numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public Collection<Penalty> getPenalties() {
        return penalties;
    }

    public void setPenalties(Collection<Penalty> penalties) {
        this.penalties = penalties;
    }

    public Collection<History> getHistories() {
        return histories;
    }

    public void setHistories(Collection<History> histories) {
        this.histories = histories;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Book[ id=" + id + " ]";
    }
    
}
