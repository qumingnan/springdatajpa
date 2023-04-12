package com.example.springdatajpa.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "classes_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<User> users;

}
