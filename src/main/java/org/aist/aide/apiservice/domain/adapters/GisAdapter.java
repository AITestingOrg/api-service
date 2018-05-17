package org.aist.aide.apiservice.domain.adapters;

import org.aist.aide.apiservice.domain.models.gis.Address;

public interface GisAdapter {
    boolean validatePostalCode(String postalCode);
    boolean validateRegion(String region);
    boolean validateFullAddress(Address address) throws Exception;
}
