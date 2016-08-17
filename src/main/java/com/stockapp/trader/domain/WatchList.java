package com.stockapp.trader.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by rajaniy on 8/16/16.
 */
public class WatchList implements Serializable {
    private Integer id;
    private String name;
    private String symbols;
    private Integer interval;
    private Float alertPrice;
    private Boolean alerted = Boolean.FALSE;
    private java.sql.Timestamp nextExecutionDate = new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbols() {
        return symbols;
    }

    public void setSymbols(String symbols) {
        this.symbols = symbols;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Float getAlertPrice() {
        return alertPrice;
    }

    public void setAlertPrice(Float alertPrice) {
        this.alertPrice = alertPrice;
    }

    public Boolean getAlerted() {
        return alerted;
    }

    public void setAlerted(Boolean alerted) {
        this.alerted = alerted;
    }

    public java.sql.Timestamp getNextExecutionDate() {
        return nextExecutionDate;
    }

    public void setNextExecutionDate(java.sql.Timestamp nextExecutionDate) {
        this.nextExecutionDate = nextExecutionDate;
    }
}
