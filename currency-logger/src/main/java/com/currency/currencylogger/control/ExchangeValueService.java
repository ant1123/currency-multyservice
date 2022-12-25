package com.currency.currencylogger.control;
import com.currency.currencylogger.data.ExchangeValue;
import com.currency.currencylogger.data.ExchangeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeValueService {
    private ExchangeValueRepository exchangeValueRepository;

    @Autowired
    public ExchangeValueService(ExchangeValueRepository exchangeValueRepository) {
        this.exchangeValueRepository = exchangeValueRepository;
    }

    public void save(ExchangeValue value) {
        exchangeValueRepository.save(value);
    }
}
