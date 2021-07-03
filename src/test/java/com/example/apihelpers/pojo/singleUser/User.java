package com.example.apihelpers.pojo.singleUser;

import com.example.apihelpers.pojo.singleResource.ResourceData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty("data")
    private UserData userData;

    @JsonProperty("support")
    private UserSupport userSupport;

    public UserData getUserData() {
        return userData;
    }

    public UserSupport getSupport() {
        return userSupport;
    }

    public String toString() {
        return "User{" +
                "data=" + userData +
                ", support=" + userSupport +
                '}';
    }
}
