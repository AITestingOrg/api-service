package org.aist.aide.apiservice.domain.models.creditcard;

import org.aist.aide.apiservice.domain.models.ValidationResult;

public class CardValidationResult implements ValidationResult {
    private boolean valid;
    private CardType cardType;
    private String error;
    private String cardNo;

    public CardValidationResult(String cardNo, String error) {
        this.cardNo = cardNo;
        this.error = error;
    }

    public CardValidationResult(String cardNo, CardType cardType) {
        this.cardNo = cardNo;
        this.valid = true;
        this.cardType = cardType;
    }

    public boolean isValid() {
        return valid;
    }

    public CardType getCardType() {
        return cardType;
    }

    public String getError() {
        return error;
    }

    public String cardNo() {
        return this.cardNo;
    }

    public String getMessage() {
        return cardNo + "    >>    " + ((valid) ? ("card: " + this.cardType) : error);
    }
}
