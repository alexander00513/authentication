package com.gmail.bogatyr.alexander.dzen.lab.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by alexander on 14.05.16.
 */
public class AuthenticationDTO {

    @JsonProperty("type")
    private String messageType;
    @JsonProperty("sequence_id")
    private UUID sequenceId;
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = LoginRequestDataDTO.class, name = "LOGIN_CUSTOMER"),
            @JsonSubTypes.Type(value = LoginResponseDataDTO.class, name = "CUSTOMER_API_TOKEN"),
            @JsonSubTypes.Type(value = LoginErrorDataDTO.class, name = "CUSTOMER_ERROR")
    })
    private AuthenticationData data;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public UUID getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(UUID sequenceId) {
        this.sequenceId = sequenceId;
    }

    public AuthenticationData getData() {
        return data;
    }

    public void setData(AuthenticationData data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationDTO that = (AuthenticationDTO) o;
        return Objects.equals(sequenceId, that.sequenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sequenceId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuthenticationDTO{");
        sb.append("messageType=").append(messageType);
        sb.append(", sequenceId=").append(sequenceId);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
