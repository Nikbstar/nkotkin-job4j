package ru.nik66;

public class Teacher extends Profession {

    String subject;

    public Teacher(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    public String getSubject() {
        return this.subject;
    }

    public Knowledge teach(Student student) {
        return new Knowledge();
    }


}
