package com.driver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Connection {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
    @ManyToOne
    @JoinColumn
    User user;

    @ManyToOne
    @JoinColumn
    ServiceProvider serviceProvider;

    public Connection(){

    }
     public Connection( User user,ServiceProvider serviceProvider) {
       this.serviceProvider=serviceProvider;
        this.user = user;
    }
    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }
    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


    
