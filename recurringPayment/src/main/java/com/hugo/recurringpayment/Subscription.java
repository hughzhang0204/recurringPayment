package com.hugo.recurringpayment;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Subscription {
    private String id;
    private Amount amount;
    private String type;
    private List<LocalDate> invoiceDates;

    public Subscription(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<LocalDate> getInvoiceDates() {
        return invoiceDates;
    }

    public void setInvoiceDates(List<LocalDate> invoiceDates) {
        this.invoiceDates = invoiceDates;
    }
}
