package com.gmail.bogatyr.alexander.dzen.lab.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Objects;

/**
 * Created by alexander on 14.05.16.
 */
@JsonTypeName("CUSTOMER_ERROR")
public class LoginErrorDataDTO extends AuthenticationData {

    @JsonProperty("error_code")
    private String code;
    @JsonProperty("error_description")
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginErrorDataDTO that = (LoginErrorDataDTO) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginErrorDataDTO{");
        sb.append("code='").append(code).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
