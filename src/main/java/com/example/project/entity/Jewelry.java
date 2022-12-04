package com.example.project.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jewelrys")
public class Jewelry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "cost")
    private Integer cost;

    @ManyToOne(targetEntity = Gem.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_gem")
    private Gem gem;

    @ManyToOne(targetEntity = Metal.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_metal", nullable = false)
    private Metal metal;

    // Public Constructor For Insert And Update
    public Jewelry(String type, Integer cost, Gem gem, Metal metal) {
        this.type = type;
        this.cost = cost;
        this.gem = gem;
        this.metal = metal;
    }

}
