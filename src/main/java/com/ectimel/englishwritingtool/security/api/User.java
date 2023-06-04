package com.ectimel.englishwritingtool.security.api;


import com.ectimel.englishwritingtool.entity.CompletedStageWord;
import com.ectimel.englishwritingtool.entity.FirstStageWord;
import com.ectimel.englishwritingtool.entity.SecondStageWord;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<FirstStageWord> firstStageWords;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<SecondStageWord> secondStageWords;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CompletedStageWord> completedStageWords;
}
