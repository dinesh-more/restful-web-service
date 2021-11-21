package com.example.restws.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 3, max = 20, message = "Name should have atleast 3 character")
    private String name;

    @Email
    private String email;

    @Size(min = 4, message = "password should have min 4 characters")
    @JsonIgnore
    private String password;

    @Past
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date DOB;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User() {
    }

    public User(int id, String name, String email, String password, Date DOB) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.DOB = DOB;
    }

    public User(String name, String email, String password, Date DOB) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.DOB = DOB;
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

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
