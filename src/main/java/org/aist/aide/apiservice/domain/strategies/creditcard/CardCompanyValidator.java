package org.aist.aide.apiservice.domain.strategies.creditcard;

import org.aist.aide.apiservice.domain.models.creditcard.CardPattern;
import org.aist.aide.apiservice.domain.models.creditcard.CardType;
import org.aist.aide.apiservice.service.repos.CardPatternRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardCompanyValidator {
    private CardPatternRepo cardPatternRepo;

    public CardCompanyValidator(@Autowired CardPatternRepo cardPatternRepo) {
        this.cardPatternRepo = cardPatternRepo;
    }

    /**
     * get an enum from a card number
     * @param card
     * @return
     */
    public CardType gleanCompany(String card) {
        for (var cardPattern : cardPatternRepo.findAll()){
            if (card.matches(cardPattern.getRegex())) {
                return new CardType(cardPattern.getIssuerName());
            }
        }
        return new CardType();
    }

    public boolean validateIssuer(String issuerName) throws Exception {
        for (var cardPattern : cardPatternRepo.findAll()){
            if (issuerName.toLowerCase().equals(cardPattern.getIssuerName().toLowerCase())) {
                return true;
            }
        }
        throw new Exception("Unknown card issuer name.");
    }
}
