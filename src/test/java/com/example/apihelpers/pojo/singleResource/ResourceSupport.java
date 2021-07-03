package com.example.apihelpers.pojo.singleResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceSupport {

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
        return "ResourceSupport{" +
                "url='" + url + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
