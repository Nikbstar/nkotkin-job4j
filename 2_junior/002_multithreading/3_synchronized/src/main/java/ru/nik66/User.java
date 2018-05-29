package ru.nik66;

public class User {

    private int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.setAmount(amount);
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}