package com.gmail.bogatyr.alexander.dzen.lab.authentication.util;

/**
 * Created by alexander on 14.05.16.
 */
public enum ErrorType {

    CUSTOMER_ERROR("customer.notFound", "Customer not found");

    private String code;
    private String description;

    ErrorType(String code, String description) {
        this.code = code;
        this.description = description;
    }

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
}
