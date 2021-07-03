package com.example.apihelpers.pojo.singleUser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserSupport {

    @JsonProperty("url")
    private String url;

    @JsonProperty("text")
    private String text;

    public String getUrl() {
        return url;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "UserSupport{" +
                "url='" + url + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
