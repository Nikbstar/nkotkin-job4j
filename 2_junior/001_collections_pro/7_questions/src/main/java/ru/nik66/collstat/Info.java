package ru.nik66.collstat;

public class Info {

    private int newUsersCount;
    private int changedUsersCount;
    private int deletedUsersCount;

    public Info(int newUsersCount, int changedUsersCount, int deletedUsersCount) {
        this.newUsersCount = newUsersCount;
        this.changedUsersCount = changedUsersCount;
        this.deletedUsersCount = deletedUsersCount;
    }

    public int getNewUsersCount() {
        return this.newUsersCount;
    }

    public int getChangedUsersCount() {
        return this.changedUsersCount;
    }

    public int getDeletedUsersCount() {
        return this.deletedUsersCount;
    }

}
