package ru.nik66;

public class Doctor extends Profession {

    private String diplom;

    public Doctor(String name, int age, String diplom) {
        super(name, age);
        this.diplom = diplom;
    }

    public String getDiplom() {
        return this.diplom;
    }

    public Diagnos heal(Pacient pacient) {
        String diagnos = String.format("Doctor %s healing %s", this.getName(), pacient.getName());
        return new Diagnos(diagnos);
    }
}
