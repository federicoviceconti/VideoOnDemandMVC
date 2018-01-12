package com.videoondemand.model;

import java.net.PasswordAuthentication;

import com.common.Field;

import javax.persistence.*;

@Entity
@Table(name = "utente", schema = "videoondemand")
@NamedQuery(name = "UtenteEntity.findUser", query = "SELECT u FROM User u where u.username = :usr and u.password = :pswd")
public class User {
    private int id;
    private String username, password;
    private int ruolo;

    public User(int id, String username, char[] password, int ruolo) {
        this.id = id;
        this.username = username;
        this.password = String.valueOf(password);
        this.ruolo = ruolo;
    }

    public User() {
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ruolo")
    public int getRuolo() {
        return ruolo;
    }

    public void setRuolo(int ruolo) {
        this.ruolo = ruolo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return this.username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return String.valueOf(this.password);
    }


}
