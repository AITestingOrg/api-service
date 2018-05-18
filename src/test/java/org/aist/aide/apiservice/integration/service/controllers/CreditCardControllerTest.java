package org.aist.aide.apiservice.integration.service.controllers;

import org.aist.aide.apiservice.domain.models.creditcard.CardPattern;
import org.aist.aide.apiservice.domain.models.creditcard.CreditCard;
import org.aist.aide.apiservice.service.controllers.CreditCardController;
import org.aist.aide.apiservice.service.repos.CardPatternRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardControllerTest {
    @Autowired
    private CardPatternRepo cardPatternRepo;

    @Autowired
    private CreditCardController creditCardController;

    @Before
    public void setup() {
        cardPatternRepo.save(new CardPattern("^4[0-9]{12}(?:[0-9]{3})?$", "Visa"));
        cardPatternRepo.save(new CardPattern("^5[1-5][0-9]{14}$", "Master"));
    }

    @After
    public void tearDown() {
        cardPatternRepo.deleteAll();
    }

    @Test
    public void givenValidCreditCard_WhenValidating_ReturnsValid() {
        // Act
        var results = creditCardController.validateCreditCard(new CreditCard(
                "4242424242424242",
                "Jack Ryan",
                "05/25",
                "123",
                null,
                "Visa"
        ));

        // Assert
        Assert.assertEquals(HttpStatus.OK, results.getStatusCode());
        Assert.assertEquals(true, results.getBody().isValid());
    }
}
