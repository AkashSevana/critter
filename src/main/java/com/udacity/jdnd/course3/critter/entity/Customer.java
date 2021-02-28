package com.udacity.jdnd.course3.critter.entity;

import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;
    private String phoneNumber;
    private String notes;

    @OneToMany(targetEntity = Pet.class)
    private List<Pet> pets;
}
