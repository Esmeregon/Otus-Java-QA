package com.example.apihelpers.pojo.singleResource;

import com.example.apihelpers.pojo.singleUser.UserSupport;
import com.example.apihelpers.pojo.singleUser.UserData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Resource {

    @JsonProperty("data")
    private ResourceData resourceData;

    @JsonProperty("support")
    private ResourceSupport resourceSupport;

    public ResourceData getResourceData() {
        return resourceData;
    }

    public ResourceSupport getSupport() {
        return resourceSupport;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceData=" + resourceData +
                ", resourceSupport=" + resourceSupport +
                '}';
    }
}
