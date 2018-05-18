package org.aist.aide.apiservice.domain.models.creditcard;

import javax.validation.constraints.NotEmpty;

public class CardPattern {
    @NotEmpty
    private String regex;
    @NotEmpty
    private String issuerName;

    public CardPattern(@NotEmpty String regex, @NotEmpty String issuerName) {
        this.regex = regex;
        this.issuerName = issuerName;
    }

    public String getRegex() {
        return regex;
    }

    public String getIssuerName() {
        return issuerName;
    }
}
