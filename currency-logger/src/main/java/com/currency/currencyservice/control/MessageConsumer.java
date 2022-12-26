package com.currency.currencyservice.control;

import com.currency.currencyservice.data.ExchangeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@EnableJms
public class MessageConsumer {

    private final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
    private @Autowired ExchangeValueService exchangeValueService;

    @JmsListener(destination = "${jms.current-topic}")
    public void receiveValue(ExchangeValue value) {
        logger.info("Received Customer message: " + value.toString());
        exchangeValueService.save(value);
    }
}