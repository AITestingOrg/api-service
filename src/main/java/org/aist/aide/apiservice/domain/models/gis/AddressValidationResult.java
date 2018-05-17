package org.aist.aide.apiservice.domain.models.gis;

import org.aist.aide.apiservice.domain.models.ValidationResult;

public class AddressValidationResult implements ValidationResult {
    private boolean valid;
    private Address address;
    private String error;

    public AddressValidationResult(Address address) {
        this.address = address;
        this.valid = true;
    }

    public AddressValidationResult(Address address, String error) {
        this.address = address;
        this.error = error;
        this.valid = false;
    }

    public AddressValidationResult() {
    }

    public boolean isValid() {
        return valid;
    }

    public Address getAddress() {
        return address;
    }

    public String getError() {
        return error;
    }
}
