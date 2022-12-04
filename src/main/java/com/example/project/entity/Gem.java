package com.example.project.entity;

import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gems")
public class Gem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "name")
    private String name;
    @Column(name = "class")
    private String gemClass;
    @OneToMany(mappedBy = "gem")
    private Set<Jewelry> jewelrys;
}
