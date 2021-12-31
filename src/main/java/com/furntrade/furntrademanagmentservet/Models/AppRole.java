package com.furntrade.furntrademanagmentservet.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppRole {
    private @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String name;
}
