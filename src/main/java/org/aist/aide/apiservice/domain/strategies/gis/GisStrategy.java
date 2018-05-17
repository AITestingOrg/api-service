package org.aist.aide.apiservice.domain.strategies.gis;

import org.aist.aide.apiservice.domain.models.gis.Address;
import org.aist.aide.apiservice.domain.models.gis.AddressValidationResult;
import org.aist.aide.apiservice.domain.strategies.ApiStrategy;

public class GisStrategy implements ApiStrategy<Address, AddressValidationResult> {
    @Override
    public AddressValidationResult run(Address obj) {
        return null;
    }
}
