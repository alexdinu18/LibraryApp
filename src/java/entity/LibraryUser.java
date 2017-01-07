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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author mdinu
 */
@Entity
@Table(name="LIBRARY_USER")
public class LibraryUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    
    @Column(name="FIRST_NAME")
    private String firstName;
    
    @Column(name="LAST_NAME")
    private String lastName;
    
    @Column(name="PASSWORD")
    private String password;
    
    @Column(name="USERNAME", unique=true)
    private String username;
    
    @NotNull
//    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
//            message = "Adresa de email introdusă nu este validă.")
    @Column(name="EMAIL", unique=true)
    private String email;
    
    @Column(name="IS_ADMIN")
    private Boolean isAdmin;
    
    @Column(name="IS_BLOCKED")
    private Boolean isBlocked;
    
    @OneToMany(cascade = ALL, mappedBy = "user")
    private Collection<History> histories;
    
    @OneToMany(cascade = ALL, mappedBy = "user")
    private Collection<Penalty> penalties;
    
    @OneToMany(cascade = ALL, mappedBy = "user")
    private Collection<Request> requests;

    @OneToOne
    @JoinColumn(name="CART_ID")
    private Cart cart;

    public LibraryUser() {
    }  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
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

    public Collection<Request> getRequests() {
        return requests;
    }

    public void setRequests(Collection<Request> requests) {
        this.requests = requests;
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
        if (!(object instanceof LibraryUser)) {
            return false;
        }
        LibraryUser other = (LibraryUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LibraryUser[ id=" + id + " ]";
    }
    
}
