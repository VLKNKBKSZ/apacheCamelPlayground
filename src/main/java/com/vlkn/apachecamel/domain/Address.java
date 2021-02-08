package com.vlkn.apachecamel.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Address {

    private String street;

    @NotNull
    @Size(min = 5, max = 150)
    private String streetName;

    @NotNull
    @DecimalMin(value = "1")
    private Integer buildingNumber;

    @NotNull
    @Size(min = 2, max = 150)
    private String city;

    @NotNull
    @DecimalMin(value = "10000")
    private Integer zipcode;

    @NotNull
    @Size(min = 2, max = 150)
    private String country;

    @JsonProperty("county_code")
    private String countyCode;
    private float latitude;
    private float longitude;
}
