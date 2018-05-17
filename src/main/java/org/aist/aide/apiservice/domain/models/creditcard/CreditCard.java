package org.aist.aide.apiservice.domain.models.creditcard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.aist.aide.apiservice.domain.models.gis.Address;

import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditCard {
    @NotEmpty
    private String number;
    private String name;
    private String expiration;
    private String cvv;
    private Address address;
    private String issuerName;

    public CreditCard(@NotEmpty String number, String name, String expiration, String cvv, Address address, String issuerName) {
        this.number = number;
        this.name = name;
        this.expiration = expiration;
        this.cvv = cvv;
        this.address = address;
        this.issuerName = issuerName;
    }

    public CreditCard() {}

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getCvv() {
        return cvv;
    }

    public Address getAddress() {
        return address;
    }

    public String getIssuerName() {
        return issuerName;
    }
}
