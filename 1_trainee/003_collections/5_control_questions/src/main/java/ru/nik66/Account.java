package ru.nik66;

import java.util.Objects;

public class Account {

    private double value;
    private String requisites;

    public boolean transferTo(Account toAcc, double value) {
        boolean result = false;
        if (this.getFromValue(value)) {
            toAcc.addToValue(value);
            result = true;
        }
        return result;
    }

    public void addToValue(double value) {
        this.setValue(this.getValue() + value);
    }

    public boolean getFromValue(double value) {
        boolean result = false;
        if (this.getValue() >= value) {
            this.setValue(this.getValue() - value);
            result = true;
        }
        return result;
    }

    public Account(double value, String requisites) {
        this.setValue(value);
        this.setRequisites(requisites);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Double.compare(account.getValue(), this.getValue()) == 0
                && this.getRequisites().equals(account.getRequisites());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getValue(), this.getRequisites());
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }
}
