package ru.nik66.sqlruparser.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Vacancy {

    private String title;
    private String link;
    private LocalDateTime date;

    public Vacancy(String title, String link, LocalDateTime date) {
        this.title = title;
        this.link = link;
        this.date = date;
    }

    public String getTitle() {
        return this.title;
    }

    public String getLink() {
        return this.link;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(this.title, vacancy.title) && Objects.equals(this.link, vacancy.link) && Objects.equals(this.date, vacancy.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.link, this.date);
    }

    @Override
    public String toString() {
        return String.format("Title='%s', link='%s', date='%s'", this.title, this.link, this.date);
    }
}
