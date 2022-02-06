package com.dina.todotogether.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class MemberInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mInfoId;

    @ManyToOne
    @JoinColumn(name = "uId", nullable = false)
    private AllUser allUser;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean enabled;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, length = 40)
    private String backupEmail;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int rpCount;

    @Column(length = 30)
    private String profile;

}
