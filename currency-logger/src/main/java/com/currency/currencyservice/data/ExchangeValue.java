package com.currency.currencyservice.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@Entity
@Table(name = "exchanged_log")
public class ExchangeValue {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "currency_from")
    private String from;
    @Column(name = "currency_to")
    private String to;
    @Column(name = "rate")
    private BigDecimal rate;
    @Column(name = "initial_value")
    private BigDecimal initialValue;
    @Column(name = "converted_value")
    private BigDecimal convertedValue;
    @Column(name = "date_time")
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
