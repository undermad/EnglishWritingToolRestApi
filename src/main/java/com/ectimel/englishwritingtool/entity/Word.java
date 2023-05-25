package com.ectimel.englishwritingtool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "words")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "english_translation", nullable = false, unique = true)
    private String englishTranslation;

    @Column(name = "polish_translation", nullable = false)
    private String polishTranslation;

    @Column(name = "in_sentence")
    private String inSentence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id", nullable = false)
    private Category category;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date lastUpdatedOn;

}
