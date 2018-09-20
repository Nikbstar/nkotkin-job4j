package ru.nik66.professions;

public class Engineer extends Profession {

    private int level;

    public Engineer(String name, int age, int level) {
        super(name, age);
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public Project make(Idea idea) {
        return new Project();
    }
}
