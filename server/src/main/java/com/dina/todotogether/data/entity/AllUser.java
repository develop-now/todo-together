package com.dina.todotogether.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "user")
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
    private Collection<Role> roles = new ArrayList<>();

    @Builder
    public AllUser(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
