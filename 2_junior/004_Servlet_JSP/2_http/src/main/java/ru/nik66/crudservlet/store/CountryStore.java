package ru.nik66.crudservlet.store;

import ru.nik66.crudservlet.model.City;
import ru.nik66.crudservlet.model.Country;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryStore {

    private static final CountryStore INSTANCE = new CountryStore();

    private final Map<City, Country> store = new HashMap<>();

    private CountryStore() {
        this.store.put(City.MILAN, Country.FR);
        this.store.put(City.PARIS, Country.FR);
        this.store.put(City.NY, Country.USA);
        this.store.put(City.WN, Country.USA);
    }

    public static CountryStore getInstance() {
        return INSTANCE;
    }

    public List<City> getCities(Country country) {
        List<City> result = new ArrayList<>();
        for (Map.Entry<City, Country> entry : this.store.entrySet()) {
            if (entry.getValue().equals(country)) {
                result.add(entry.getKey());
            }
        }
        return result;
    }

    public boolean isCityInCountry(Country country, City city) {
        return this.store.get(city).equals(country);
    }
}
