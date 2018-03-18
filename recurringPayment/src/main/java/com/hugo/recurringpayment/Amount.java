package com.hugo.recurringpayment;

public class Amount {
    private double value;
    private String currency;

    public Amount(double value, String currenty){
        this.value = value;
        this.currency = currenty;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
