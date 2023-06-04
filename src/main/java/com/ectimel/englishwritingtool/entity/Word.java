package com.ectimel.englishwritingtool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

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

    @Column(name = "english_translation", nullable = false, unique = true, length = 20)
    private String englishTranslation;

    @Column(name = "in_sentence")
    private String inSentence;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "word", fetch = FetchType.LAZY)
    private List<FirstStageWord> firstStageWords;

    @OneToMany(mappedBy = "word", fetch = FetchType.LAZY)
    private List<SecondStageWord> secondStageWords;

    @OneToMany(mappedBy = "word", fetch = FetchType.LAZY)
    private List<CompletedStageWord> completedStageWords;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date lastUpdatedOn;



}
