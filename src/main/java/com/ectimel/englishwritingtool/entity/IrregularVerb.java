package com.ectimel.englishwritingtool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "irregular_verbs")
public class IrregularVerb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_form", nullable = false, unique = true)
    private String firstForm;

    @Column(name = "second_form", nullable = false)
    private String secondForm;

    @Column(name = "third_form", nullable = false)
    private String thirdForm;

    @Column(name = "polish_translation", nullable = false)
    private String polishTranslation;

}
