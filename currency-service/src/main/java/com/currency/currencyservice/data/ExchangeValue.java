package com.currency.currencyservice.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ExchangeValue {

    private String from;
    private String to;
    private BigDecimal rate;
    private BigDecimal initialValue;
    private BigDecimal convertedValue;
    private LocalDateTime date;

    public ExchangeValue() {}

    public ExchangeValue(String from, String to, BigDecimal rate, BigDecimal initialValue, BigDecimal convertedValue) {
        super();
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.initialValue = initialValue;
        this.convertedValue = convertedValue;
        this.date = LocalDateTime.now();
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getInitialValue() {
        return initialValue;
    }

    public BigDecimal getConvertedValue() {
        return convertedValue;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
