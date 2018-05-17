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
        for (CardPattern cardPattern : cardPatternRepo.findAll()){
            if (card.matches(cardPattern.getRegex())) {
                return new CardType(cardPattern.getIssuerName());
            }
        }
        return new CardType();
    }
}
