package ru.graduateproject.model;


import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.CollectionUtils;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "users_unique_name_idx")})
public class User extends AbstractNamedEntity{

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 64)
    private String password;

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    public User() {
    }

    public User(Integer id, String name, @NotBlank @Size(min = 5, max = 64) String password, Set<Role> roles) {
        super(id, name);
        this.password = password;
        this.roles = roles;
    }
    public User(Integer id, String name, @NotBlank @Size(min = 5, max = 64) String password, Role role, Role... roles) {
        super(id, name);
        this.password = password;
        this.roles = EnumSet.of(role, roles);
    }

    public User(User user){
        this(user.getId(), user.getName(), user.getPassword(), user.getRoles());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "roles=" + roles +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
