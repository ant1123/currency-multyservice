package com.currency.currencyservice.controller;
import com.currency.currencyservice.data.ExchangeValue;
import com.currency.currencyservice.data.Currency;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.List;
import org.slf4j.Logger;


@RestController
public class CurrencyExchangeController {
    private ExchangeValueService exchangeValueService;
    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired RestTemplate restTemplate;
    @Autowired
    public CurrencyExchangeController(ExchangeValueService exchangeValueService) {
        this.exchangeValueService = exchangeValueService;
    }

    @RequestMapping(
            value = "/currencies",
            method = RequestMethod.GET,
            produces="application/xml"
    )
    public List<Currency> getCurrencies()
    {
        return exchangeValueService.getAllCurrencies();
    }

    @RequestMapping(
            value = "/currency-exchange/from/{from}/to/{to}",
            method = RequestMethod.GET,
            produces="application/json"
    )
    public ExchangeValue retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to,
            @RequestParam(required = false, name="value", defaultValue = "1" ) String value)
    {
        ExchangeValue exchangeValue = exchangeValueService.convert(from, to, value);
        //TODO here better to use async way of communication between services as we do not need to wait for answer from client
        try {
            RequestEntity<ExchangeValue> requestEntity = RequestEntity
                    .post(new URL("http://currency-logger-service/exchanges").toURI())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(exchangeValue);
            ResponseEntity<?> response = restTemplate.exchange(requestEntity, ExchangeValue.class);
            logger.info("ExchangeValue was sent to database, status code  "+ response.getStatusCode());
        }
        catch(Exception e) {
            logger.error("Error writing to the ExchangeValue data base "+ e.getMessage());
        }
        return exchangeValue;
    }
}