package org.aist.aide.apiservice.unit.domain.strategies;

import org.aist.aide.apiservice.domain.models.creditcard.CardType;
import org.aist.aide.apiservice.domain.models.creditcard.CreditCard;
import org.aist.aide.apiservice.domain.strategies.creditcard.CardCompanyValidator;
import org.aist.aide.apiservice.domain.strategies.creditcard.CreditCardStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class CreditCardStrategyTest {
    @Mock
    private CardCompanyValidator cardCompanyValidator;

    @InjectMocks
    private CreditCardStrategy creditCardStrategy;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    private CreditCard getValidCard() {
        return new CreditCard(
                "4242424242424242",
                "Jack Ryan",
                "05/25",
                "123",
                null,
                "Visa"
        );
    }

    @Test
    public void givenValidCreditCard_WhenValidating_ValidationReturnsValid() throws Exception {
        // Arrange
        var validCard = getValidCard();
        when(cardCompanyValidator.gleanCompany(validCard.getNumber())).thenReturn(new CardType("Visa"));
        when(cardCompanyValidator.validateIssuer(validCard.getIssuerName())).thenReturn(true);

        // Act
        var result = creditCardStrategy.run(validCard);

        // Assert
        Assert.assertEquals(true, result.isValid());
    }

    @Test
    public void givenInvalidNumberCreditCard_WhenValidating_ValidationReturnsInvalid() throws Exception {
        // Arrange
        var validCard = new CreditCard(
                "42424242442",
                "Jack Ryan",
                "05/25",
                "123",
                null,
                "Visa"
        );;
        when(cardCompanyValidator.gleanCompany(validCard.getNumber())).thenReturn(new CardType("Visa"));
        when(cardCompanyValidator.validateIssuer(validCard.getIssuerName())).thenReturn(true);

        // Act
        var result = creditCardStrategy.run(validCard);

        // Assert
        Assert.assertEquals(false, result.isValid());
    }

    @Test
    public void givenInvalidIssuerCreditCard_WhenValidating_ValidationReturnsInvalid() throws Exception {
        // Arrange
        var validCard = new CreditCard(
                "42424242442",
                "Jack Ryan",
                "05/25",
                "123",
                null,
                "Viva"
        );;
        when(cardCompanyValidator.gleanCompany(validCard.getNumber())).thenReturn(new CardType("Visa"));
        when(cardCompanyValidator.validateIssuer(validCard.getIssuerName())).thenReturn(true);

        // Act
        var result = creditCardStrategy.run(validCard);

        // Assert
        Assert.assertEquals(false, result.isValid());
    }

    @Test
    public void givenInvalidCvvCreditCard_WhenValidating_ValidationReturnsInvalid() throws Exception {
        // Arrange
        var validCard = new CreditCard(
                "4242424242424242",
                "Jack Ryan",
                "05/25",
                "99999",
                null,
                "Visa"
        );
        when(cardCompanyValidator.gleanCompany(validCard.getNumber())).thenReturn(new CardType("Visa"));
        when(cardCompanyValidator.validateIssuer(validCard.getIssuerName())).thenReturn(true);

        // Act
        var result = creditCardStrategy.run(validCard);

        // Assert
        Assert.assertEquals(false, result.isValid());
    }

    @Test
    public void givenInvalidCvv1CreditCard_WhenValidating_ValidationReturnsInvalid() throws Exception {
        // Arrange
        var validCard = new CreditCard(
                "4242424242424242",
                "Jack Ryan",
                "05/25",
                "the",
                null,
                "Visa"
        );
        when(cardCompanyValidator.gleanCompany(validCard.getNumber())).thenReturn(new CardType("Visa"));
        when(cardCompanyValidator.validateIssuer(validCard.getIssuerName())).thenReturn(true);

        // Act
        var result = creditCardStrategy.run(validCard);

        // Assert
        Assert.assertEquals(false, result.isValid());
    }

    @Test
    public void givenInvalidCvv2CreditCard_WhenValidating_ValidationReturnsInvalid() throws Exception {
        // Arrange
        var validCard = new CreditCard(
                "4242424242424242",
                "Jack Ryan",
                "05/25",
                "12",
                null,
                "Visa"
        );
        when(cardCompanyValidator.gleanCompany(validCard.getNumber())).thenReturn(new CardType("Visa"));
        when(cardCompanyValidator.validateIssuer(validCard.getIssuerName())).thenReturn(true);

        // Act
        var result = creditCardStrategy.run(validCard);

        // Assert
        Assert.assertEquals(false, result.isValid());
    }

    @Test
    public void givenInvalidCvv3CreditCard_WhenValidating_ValidationReturnsInvalid() throws Exception {
        // Arrange
        var validCard = new CreditCard(
                "4242424242424242",
                "Jack Ryan",
                "05/25",
                "1.2",
                null,
                "Visa"
        );
        when(cardCompanyValidator.gleanCompany(validCard.getNumber())).thenReturn(new CardType("Visa"));
        when(cardCompanyValidator.validateIssuer(validCard.getIssuerName())).thenReturn(true);

        // Act
        var result = creditCardStrategy.run(validCard);

        // Assert
        Assert.assertEquals(false, result.isValid());
    }

    @Test
    public void givenInvalidExpirationCreditCard_WhenValidating_ValidationReturnsInvalid() throws Exception {
        // Arrange
        var validCard = new CreditCard(
                "4242424242424242",
                "Jack Ryan",
                "hi",
                "123",
                null,
                "Visa"
        );
        when(cardCompanyValidator.gleanCompany(validCard.getNumber())).thenReturn(new CardType("Visa"));
        when(cardCompanyValidator.validateIssuer(validCard.getIssuerName())).thenReturn(true);

        // Act
        var result = creditCardStrategy.run(validCard);

        // Assert
        Assert.assertEquals(false, result.isValid());
    }

    @Test
    public void givenInvalidExpiration1CreditCard_WhenValidating_ValidationReturnsInvalid() throws Exception {
        // Arrange
        var validCard = new CreditCard(
                "4242424242424242",
                "Jack Ryan",
                "01/05/2017",
                "123",
                null,
                "Visa"
        );
        when(cardCompanyValidator.gleanCompany(validCard.getNumber())).thenReturn(new CardType("Visa"));
        when(cardCompanyValidator.validateIssuer(validCard.getIssuerName())).thenReturn(true);

        // Act
        var result = creditCardStrategy.run(validCard);

        // Assert
        Assert.assertEquals(false, result.isValid());
    }

    @Test
    public void givenExpiredCreditCard_WhenValidating_ValidationReturnsInvalid() throws Exception {
        // Arrange
        var validCard = new CreditCard(
                "4242424242424242",
                "Jack Ryan",
                "05/17",
                "123",
                null,
                "Visa"
        );
        when(cardCompanyValidator.gleanCompany(validCard.getNumber())).thenReturn(new CardType("Visa"));
        when(cardCompanyValidator.validateIssuer(validCard.getIssuerName())).thenReturn(true);

        // Act
        var result = creditCardStrategy.run(validCard);

        // Assert
        Assert.assertEquals(false, result.isValid());
    }
}
