package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.pet.PetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue
    private Long id;

    private PetType type;

    @Nationalized
    private String name;

    @ManyToOne(targetEntity = Customer.class, optional = false)
    private Customer customer;

    private LocalDate birthDate;
    private String notes;
}
