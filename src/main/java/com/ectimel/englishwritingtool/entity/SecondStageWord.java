package com.ectimel.englishwritingtool.entity;

import com.ectimel.englishwritingtool.security.api.User;
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
@Table(name = "second_stage_words")
public class SecondStageWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "words_id", nullable = false)
    private Word word;

    @Column(name = "user_translation", nullable = false, length = 20)
    private String userTranslation;

    @Column(name = "correct", nullable = false)
    private int correct;

    @Column(name = "incorrect", nullable = false)
    private int incorrect;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}
