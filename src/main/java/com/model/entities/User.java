package com.model.entities;

import javax.persistence.*;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String newName;

    @Column
    private String newLastName;

    @Column
    private String eMail;

    @Column
    private String pass;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", newName='" + newName + '\'' +
                ", newLastName='" + newLastName + '\'' +
                ", eMail='" + eMail + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public User() {
    }

    public User(String newName, String newLastName, String eMail, String pass) {

        this.newName = newName;
        this.newLastName = newLastName;
        this.eMail = eMail;
        this.pass = pass;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewLastName() {
        return newLastName;
    }

    public void setNewLastName(String newLastName) {
        this.newLastName = newLastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
