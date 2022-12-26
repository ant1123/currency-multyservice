package com.currency.currencyservice.controller;
import com.currency.currencyservice.data.CurrenciesRepository;
import com.currency.currencyservice.data.Currency;
import com.currency.currencyservice.data.ExchangeValue;
import com.currency.currencyservice.util.ActiveMQConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import javax.transaction.Transactional;
import java.lang.IllegalStateException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Service
public class ExchangeValueService {
    private CurrenciesRepository currenciesRepository;
    private Logger logger = LoggerFactory.getLogger(ExchangeValueService.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    public ExchangeValueService(CurrenciesRepository currenciesRepository) {
        this.currenciesRepository = currenciesRepository;
    }

    public List<Currency> getAllCurrencies() {
        return currenciesRepository.findAll();
    }

    @Transactional
    public ExchangeValue convert(String fromName, String toName, String value) {
        Currency from = currenciesRepository.findById(fromName).orElseThrow(
                () -> new IllegalStateException("Not found initial currency "));
        Currency to = currenciesRepository.findById(toName).orElseThrow(
                () -> new IllegalStateException("Not found converted currency "));
        BigDecimal fromRate = from.getRate();
        BigDecimal toRate = to.getRate();
        BigDecimal initialValue = new BigDecimal(value);
        if (fromRate == null || toRate == null || BigDecimal.ZERO.equals(fromRate)) {
            throw new IllegalStateException("Incorrect rates information");
        }
        BigDecimal rate = toRate.divide(fromRate, RoundingMode.HALF_UP);
        BigDecimal convertedValue = initialValue.multiply(rate);
        return new ExchangeValue(fromName, toName, rate, initialValue, convertedValue);
    }

    public void sendExchangeValue(ExchangeValue value){
        logger.info("Sending customer message" + value + " to queue " + ActiveMQConfiguration.VALUE_QUEUE);
        jmsTemplate.convertAndSend(ActiveMQConfiguration.VALUE_QUEUE, value);
    }
}
