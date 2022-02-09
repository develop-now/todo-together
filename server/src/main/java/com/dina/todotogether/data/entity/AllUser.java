package com.dina.todotogether.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

import static javax.persistence.FetchType.EAGER;

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
    @JoinTable(name = "userRole",
            joinColumns = @JoinColumn(name="uId"),
            inverseJoinColumns = @JoinColumn(name="rId")
    )
    private Collection<Role> roles = new HashSet<>();

}
