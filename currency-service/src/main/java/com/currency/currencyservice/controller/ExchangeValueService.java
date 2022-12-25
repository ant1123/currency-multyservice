package com.currency.currencyservice.controller;
import com.currency.currencyservice.data.CurrenciesRepository;
import com.currency.currencyservice.data.Currency;
import com.currency.currencyservice.data.ExchangeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Service
public class ExchangeValueService {
    private CurrenciesRepository currenciesRepository;

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
}
