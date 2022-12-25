package com.currency.currencyservice.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "CURRENCIES")
public class Currency {
    @Id
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "RATE")
    private BigDecimal rate;

    public Currency() {}

    public Currency(String name, String description, BigDecimal rate) {
        super();
        this.name = name;
        this.description = description;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
