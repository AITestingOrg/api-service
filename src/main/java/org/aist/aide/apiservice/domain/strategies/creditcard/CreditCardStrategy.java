package org.aist.aide.apiservice.domain.strategies.creditcard;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.aist.aide.apiservice.domain.models.creditcard.CardValidationResult;
import org.aist.aide.apiservice.domain.models.creditcard.CreditCard;
import org.aist.aide.apiservice.domain.models.gis.Address;
import org.aist.aide.apiservice.domain.strategies.ApiStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("creditCardStrategy")
public class CreditCardStrategy implements ApiStrategy<CreditCard, CardValidationResult> {

    private CardCompanyValidator cardCompanyValidator;

    public CreditCardStrategy(@Autowired CardCompanyValidator cardCompanyValidator) {
        this.cardCompanyValidator = cardCompanyValidator;
    }

    /**
     * Checks if the field is a valid credit card number.
     * @param cardIn The card number to validate.
     * @return Whether the card number is valid.
     */
    private CardValidationResult validateNumber(final String cardIn) {
        var card = cardIn.replaceAll("[^0-9]+", ""); // remove all non-numerics
        if ((card == null) || (card.length() < 13) || (card.length() > 19)) {
            return new CardValidationResult(card,"failed length check");
        }

        if (!luhnCheck(card)) {
            return new CardValidationResult(card,"failed luhn check");
        }

        var company = cardCompanyValidator.gleanCompany(card);
        if (company == null) return new CardValidationResult(card,"failed card company check");

        return new CardValidationResult(card, company);
    }

    /**
     * Checks for a valid credit card number.
     * @param cardNumber Credit Card Number.
     * @return Whether the card number passes the luhnCheck.
     */
     private boolean luhnCheck(String cardNumber) {
        // number must be validated as 0..9 numeric first!!
        var digits = cardNumber.length();
        var oddOrEven = digits & 1;
        long sum = 0;
        for (int count = 0; count < digits; count++) {
            int digit = 0;
            try {
                digit = Integer.parseInt(cardNumber.charAt(count) + "");
            } catch(NumberFormatException e) {
                return false;
            }

            if (((count & 1) ^ oddOrEven) == 0) { // not
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }

        return (sum == 0) ? false : (sum % 10 == 0);
    }

    private boolean validateExpiration(String date) throws Exception {
         try {
             DateTimeFormatter parser = DateTimeFormatter.ofPattern("MM/yy");
             var expiration = YearMonth.parse(date, parser).atDay(1);
             if (LocalDate.now().isBefore(expiration)) {
                 return true;
             }
         } catch(NullPointerException e) {
             throw new Exception("Invalid or bad expiration date.");
         } catch(DateTimeParseException e) {
             throw new Exception("Invalid or bad expiration date, expected format is MM/YY, M/YYYY, or similar.");
         }
         throw new Exception("Card is expired.");
    }

    private boolean validateCvv(String cvv) throws Exception {
         try {
             Integer.parseInt(cvv);
         } catch(NumberFormatException e) {
             throw new Exception("CVV invalid format, integer expected.");
         } catch (NullPointerException e) {
             throw new Exception("CVV invalid format, integer expected.");
         }
        if(cvv.length() > 4 || cvv.length() < 3) {
             throw new Exception("CVV invalid format, length should be between 3-4.");
        }
        return true;
    }

    private boolean validateIssuerName(String issuerName) throws Exception {
         return cardCompanyValidator.validateIssuer(issuerName);
    }

    private boolean validateAddress(Address address) {
        return true;
    }

    @Override
    public CardValidationResult run(CreditCard card) {
         try {
             if(card.getExpiration() != null && !card.getExpiration().isEmpty()) {
                 validateExpiration(card.getExpiration());
             }
             if(card.getCvv() != null && !card.getCvv().isEmpty()) {
                 validateCvv(card.getCvv());
             }
             if(card.getIssuerName() != null && !card.getIssuerName().isEmpty()) {
                 validateIssuerName(card.getIssuerName());
             }
             if(card.getAddress() != null) {
                 validateAddress(card.getAddress());
             }
             return validateNumber(card.getNumber());
         } catch(Exception e) {
            return new CardValidationResult(card.getNumber(), e.getMessage());
         }
    }
}
