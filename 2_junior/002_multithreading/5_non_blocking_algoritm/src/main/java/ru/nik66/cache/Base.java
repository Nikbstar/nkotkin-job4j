package ru.nik66.cache;

public class Base {

    private final int id;
    private int version;

    public Base(int id, int version) {
        this.id = id;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Base base = (Base) o;

        return id == base.id && version == base.version;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + version;
        return result;
    }

}
