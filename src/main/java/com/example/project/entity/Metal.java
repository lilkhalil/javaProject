package com.example.project.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "metals")
public class Metal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sample")
    private Integer sample;
    @Column(name = "name")
    private String name;
    @Column(name = "density")
    private Float density;
    @OneToMany(mappedBy = "metal", cascade = CascadeType.ALL)
    private Set<Jewelry> jewelrys;
}
