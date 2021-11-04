package com.furntrade.furntrademanagmentservet.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
public class AppUser {
    private @Id
    @GeneratedValue
    Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String placeOfWork;
    private String email;
    //ToDO add this properties later
    //private String password;
    private AppRole role;

    public AppUser() {
    }

    public AppUser(String username) {
        this.username = username;
    }

    public AppUser(String firstName, String username, String lastName,AppRole role) {
        this.firstName = firstName;
        this.username = username;
        this.lastName = lastName;
        this.role=role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppRole getRole() {
        return role;
    }

    public void setRole(AppRole role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id.equals(appUser.id) && Objects.equals(username, appUser.username) && Objects.equals(firstName, appUser.firstName) && Objects.equals(lastName, appUser.lastName) && Objects.equals(placeOfWork, appUser.placeOfWork) && Objects.equals(email, appUser.email) && role == appUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, lastName, placeOfWork, email, role);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", placeOfWork='" + placeOfWork + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
