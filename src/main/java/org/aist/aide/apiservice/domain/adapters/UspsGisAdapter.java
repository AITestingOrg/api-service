package org.aist.aide.apiservice.domain.adapters;

import org.aist.aide.apiservice.domain.models.gis.Address;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("uspsGisAdapter")
public class UspsGisAdapter implements GisAdapter {

    @Value("${aide.gis.usps.user}")
    private String userId;

    @Override
    public boolean validatePostalCode(String postalCode) {
        return false;
    }

    @Override
    public boolean validateRegion(String region) {
        return false;
    }

    @Override
    public boolean validateFullAddress(Address address) throws Exception {
        String addressValidationXml = String.format(
                "<AddressValidateRequest USERID=\"%s\">" +
                "<Revision>1</Revision>" +
                "  <Address ID=\"0\">" +
                "    <Address1></Address1>" +
                "    <Address2>%s</Address2>" +
                "    <City>%s</City>" +
                "    <State>%s</State>" +
                "    <Zip5>%s</Zip5>" +
                "    <Zip4></Zip4>" +
                "  </Address>" +
                "</AddressValidateRequest>",
                userId,
                address.getStreet() + " " + address.getApartmentNumber(),
                address.getCity(),
                address.getRegion(),
                address.getPostalCode());

        String requestUrl = "http://production.shippingapis.com/ShippingAPI.dll?API=Verify&XML=" + addressValidationXml;
        var restTemplate = new RestTemplate();
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        var request = new HttpEntity<String>("", headers);
        var response = restTemplate.exchange(requestUrl, HttpMethod.GET, request, String.class);
        // todo: refactor this with XML deserialization
        if(response.getBody().contains("<Error>")) {
            String regexString = Pattern.quote("<Description>") + "(.*?)" + Pattern.quote("</Description>");
            Pattern pattern = Pattern.compile(regexString);
            Matcher matcher = pattern.matcher(response.getBody());
            while (matcher.find()) {
                throw new Exception(matcher.group(1));
            }
        }
        return true;
    }
}