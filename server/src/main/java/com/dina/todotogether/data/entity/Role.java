package com.dina.todotogether.data.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rId;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private RoleType rName;

}
