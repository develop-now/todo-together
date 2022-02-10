package com.dina.todotogether.data.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "user")
@Builder
@Entity
public class AllUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long uId;

    @Column(nullable = false, length = 40)
    private String email;

    @Column(nullable = false, length = 70)
    private String password;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "usersRoles",
            joinColumns = @JoinColumn(name="uId"),
            inverseJoinColumns = @JoinColumn(name="rId")
    )
    private Collection<Role> roles = new HashSet<>();

}
