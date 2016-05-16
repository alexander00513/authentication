package com.gmail.bogatyr.alexander.dzen.lab.authentication.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by alexander on 14.05.16.
 */
@Entity
public class Authentication {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Length(max = 128)
    @Column(unique = true)
    private String email;

    @NotBlank
    @Length(max = 128)
    private String password;

    private UUID token;
    private ZonedDateTime tokenExpire;

    // TODO: 14.05.16 maybe use Audit log from hibernate

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public ZonedDateTime getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(ZonedDateTime tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authentication that = (Authentication) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, token);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Authentication{");
        sb.append("id=").append(id);
        sb.append(", login='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", token=").append(token);
        sb.append(", tokenExpire=").append(tokenExpire);
        sb.append('}');
        return sb.toString();
    }
}
