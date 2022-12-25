package com.currency.currencylogger.control;
import com.currency.currencylogger.data.ExchangeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExchangeValueController {
    private ExchangeValueService exchangeValueService;
    private Logger logger = LoggerFactory.getLogger(ExchangeValueController.class);

    @Autowired
    public ExchangeValueController(ExchangeValueService exchangeValueService) {
        this.exchangeValueService = exchangeValueService;
    }
    @PostMapping("/exchanges")
    public void saveExchangeValue(@RequestBody ExchangeValue value)
    {
        logger.info("Saving new exchange value");
        exchangeValueService.save(value);
    }

}
