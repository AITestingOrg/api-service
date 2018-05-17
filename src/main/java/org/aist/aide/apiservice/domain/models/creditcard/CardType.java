package org.aist.aide.apiservice.domain.models.creditcard;

public class CardType {
    private final boolean valid;
    private final String company;

    public CardType(String company) {
        this.valid = true;
        this.company = company;
    }

    public CardType() {
        this.valid = false;
        this.company = null;
    }

    public boolean isValid() {
        return valid;
    }

    public String getCompany() {
        return company;
    }
}
