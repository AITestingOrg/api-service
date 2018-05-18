package org.aist.aide.apiservice.service.controllers;

import org.aist.aide.apiservice.domain.models.creditcard.CardValidationResult;
import org.aist.aide.apiservice.domain.models.creditcard.CreditCard;
import org.aist.aide.apiservice.domain.strategies.ApiStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/creditcard")
public class CreditCardController {

    private ApiStrategy<CreditCard, CardValidationResult> creditCardStrategy;

    public CreditCardController(@Autowired ApiStrategy creditCardStrategy) {
        this.creditCardStrategy = creditCardStrategy;
    }

    @RequestMapping("/")
    public ResponseEntity<CardValidationResult> validateCreditCard(@RequestBody CreditCard creditCard) {
        return new ResponseEntity<>(creditCardStrategy.run(creditCard), HttpStatus.OK);
    }
}
