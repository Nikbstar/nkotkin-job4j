package ru.nik66;

import java.util.ArrayList;
import java.util.List;

public class PhoneDictionary {

    private List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    // Поиск мы должны осуществлять по всем полям через метод String.contains.
    // Например. Арсентьев, Петр, Брянск, 53742. Если мы ищем по слово "нкс" или 537 или Арс.
    // Мы всегда должны вернуть этого пользователя.
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person person : this.persons) {
            if (person.getName().contains(key)
                    || person.getSurname().contains(key)
                    || person.getPhone().contains(key)
                    || person.getAddress().contains(key)) {
                result.add(person);
            }
        }
        return result;
    }

}
