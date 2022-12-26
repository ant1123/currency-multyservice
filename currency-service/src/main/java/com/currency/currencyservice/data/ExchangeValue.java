package com.currency.currencyservice.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
public class ExchangeValue {

    private String from;
    private String to;
    private BigDecimal rate;
    private BigDecimal initialValue;
    private BigDecimal convertedValue;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime date;

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
