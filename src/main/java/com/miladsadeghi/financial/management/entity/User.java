package com.miladsadeghi.financial.management.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@AllArgsConstructor

@Entity(name = "user")
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(name = "userName_unique",columnNames = "username")
        }
)
public class User {
    @Id
    @SequenceGenerator(
            name = "USER_SEQ",
            sequenceName = "USER_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "USER_SEQ"
    )
    @Column(
            name = "id",
            updatable = false,
            unique = true,
            nullable = false
    )
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String username;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false)
    private Long credit;
    public User() {

    }
}
