package ru.nik66;

import java.util.Objects;

public class User {

    private int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.setAmount(amount);
    }

    public int getId() {
        return this.id;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return this.id == user.id
                && this.amount == user.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.amount);
    }
}