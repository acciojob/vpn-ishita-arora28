package com.driver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ServiceProvider {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @ManyToOne
    @JoinColumn
    Admin admin;

    @ManyToMany(mappedBy = "serviceProviderList", cascade = CascadeType.ALL)
    List<User> users=new ArrayList<>();

    @OneToMany(mappedBy = "serviceProvider", cascade = CascadeType.ALL)
    List<Connection> conectionList=new ArrayList<>();

    @OneToMany(mappedBy = "serviceProvider", cascade = CascadeType.ALL)
    List<Country> countryList=new ArrayList<>();

    public ServiceProvider(){

    }
    public ServiceProvider(String name, Admin admin) {
        this.name = name;
        this.admin = admin;
        this.users=new ArrayList<>();
        this.conectionList=new ArrayList<>();
        this.countryList=new ArrayList<>();
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Connection> getConectionList() {
        return conectionList;
    }

    public void setConectionList(List<Connection> conectionList) {
        this.conectionList = conectionList;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    
}
