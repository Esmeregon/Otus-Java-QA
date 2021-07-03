package com.example.apihelpers.pojo.singleResource;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceData {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("year")
    private Integer year;

    @JsonProperty("color")
    private String color;

    @JsonProperty("pantone_value")
    private String pantoneValue;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public String getPantoneValue() {
        return pantoneValue;
    }

    @Override
    public String toString() {
        return "ResourceData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", pantoneValue='" + pantoneValue + '\'' +
                '}';
    }
}
