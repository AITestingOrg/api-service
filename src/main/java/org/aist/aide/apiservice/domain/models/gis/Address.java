package org.aist.aide.apiservice.domain.models.gis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    private String street;
    private String city;
    private String region;
    private String country;
    private String postalCode;
    private String apartmentNumber;

    public Address(
            String street,
            String city,
            String region,
            String country,
            String postalCode,
            String apartmentNumber) {
        this.street = street;
        this.city = city;
        this.region = region;
        this.country = country;
        this.postalCode = postalCode;
        this.apartmentNumber = apartmentNumber;
    }

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }
}
