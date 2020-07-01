package org.fasttrackit.walletmanager.transfer;

import java.sql.Date;

public class UpdateTransactionRequest {

    private String description;
    private double value;
    private String type;
    private Date date;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CreateTransaction{" +
                "description='" + description + '\'' +
                ", value=" + value +
                ", type='" + type + '\'' +
                ", date=" + date +
                '}';
    }

}
