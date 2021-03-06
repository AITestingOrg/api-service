package org.aist.aide.apiservice.unit.domain.strategies;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.aist.aide.apiservice.domain.models.creditcard.CardPattern;
import org.aist.aide.apiservice.domain.strategies.creditcard.CardCompanyValidator;
import org.aist.aide.apiservice.service.repos.CardPatternRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CardCompanyValidatorTest {
    @Mock
    private CardPatternRepo cardPatternRepo;

    @InjectMocks
    private CardCompanyValidator cardCompanyValidator;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    public List<CardPattern> setupVisaAndMaster() {
        var list = new ArrayList<CardPattern>();
        list.add(new CardPattern("^4[0-9]{12}(?:[0-9]{3})?$", "Visa"));
        list.add(new CardPattern("^5[1-5][0-9]{14}$", "Master"));
        return list;
    }

    @Test
    public void givenAPatternExists_whenGleaningIssuer_TheCorrectIssuerIsReturned() {
        // Arrange
        when(cardPatternRepo.findAll()).thenReturn(setupVisaAndMaster());

        // Act
        var result = cardCompanyValidator.gleanCompany("4242424242424242");

        // Assert
        Assert.assertEquals("Visa", result.getCompany());
        Assert.assertEquals(true, result.isValid());
    }

    @Test
    public void givenAInvalidCardNumber_whenGleaningIssuer_InvalidIsReturned() {
        // Arrange
        when(cardPatternRepo.findAll()).thenReturn(setupVisaAndMaster());

        // Act
        var result = cardCompanyValidator.gleanCompany("4244242");

        // Assert
        Assert.assertEquals(false, result.isValid());
    }

    @Test
    public void givenIssuerExists_whenValidatingIssuer_TrueReturned() throws Exception {
        // Arrange
        when(cardPatternRepo.findAll()).thenReturn(setupVisaAndMaster());

        // Act
        var result = cardCompanyValidator.validateIssuer("Visa");

        // Assert
        Assert.assertEquals(true, result);
    }

    @Test(expected = Exception.class)
    public void givenIssuerDoesNotExist_whenValidatingIssuer_ExceptionThrown() throws Exception {
        // Arrange
        when(cardPatternRepo.findAll()).thenReturn(setupVisaAndMaster());

        // Act
        cardCompanyValidator.validateIssuer("Lol");
    }
}
