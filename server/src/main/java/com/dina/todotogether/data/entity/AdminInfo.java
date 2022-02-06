package com.dina.todotogether.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class AdminInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long aInfoId;

    @ManyToOne
    @JoinColumn(name = "uId", nullable = false)
    private AllUser allUser;

    @Column(nullable = false, length = 70)
    private String usingEmail;

}
