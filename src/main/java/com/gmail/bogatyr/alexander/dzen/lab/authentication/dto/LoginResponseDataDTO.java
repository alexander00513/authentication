package com.gmail.bogatyr.alexander.dzen.lab.authentication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by alexander on 14.05.16.
 */
@JsonTypeName("CUSTOMER_API_TOKEN")
public class LoginResponseDataDTO extends AuthenticationData {

    @JsonProperty("api_token")
    private UUID apiToken;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @JsonProperty("api_token_expiration_date")
    private ZonedDateTime apiTokenExpirationDate;

    public UUID getApiToken() {
        return apiToken;
    }

    public void setApiToken(UUID apiToken) {
        this.apiToken = apiToken;
    }

    public ZonedDateTime getApiTokenExpirationDate() {
        return apiTokenExpirationDate;
    }

    public void setApiTokenExpirationDate(ZonedDateTime apiTokenExpirationDate) {
        this.apiTokenExpirationDate = apiTokenExpirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginResponseDataDTO that = (LoginResponseDataDTO) o;
        return Objects.equals(apiToken, that.apiToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiToken);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginResponseDataDTO{");
        sb.append("apiToken='").append(apiToken).append('\'');
        sb.append(", apiTokenExpirationDate='").append(apiTokenExpirationDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
