package com.example.springdatajpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_name")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "classes_id", referencedColumnName = "id")
    private Classes classes;

}
