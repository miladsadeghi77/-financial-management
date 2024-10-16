package com.miladsadeghi.financial.management.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @Column(nullable = false, unique = true, length = 60)
    private String username;

    @Column(nullable = false, length = 150)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<UserRole> roles = new HashSet<>();

    private Long credit;
    public User() {

    }
}
