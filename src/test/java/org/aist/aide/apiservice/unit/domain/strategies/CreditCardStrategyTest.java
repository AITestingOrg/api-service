package org.aist.aide.apiservice.unit.domain.strategies;

import org.aist.aide.apiservice.domain.strategies.creditcard.CardCompanyValidator;
import org.aist.aide.apiservice.domain.strategies.creditcard.CreditCardStrategy;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CreditCardStrategyTest {
    @Mock
    private CardCompanyValidator cardCompanyValidator;

    @InjectMocks
    private CreditCardStrategy creditCardStrategy;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenValidCreditCard_WhenValidating_ValidationReturnsValid() {

    }
}
