package Dao;

import DataModel.Country;
import javafx.collections.ObservableList;

public interface CountryDao {
    ObservableList<Country> getAllCountries();
    Country getCountryByName(String countryName);
    boolean deleteCountry(Country country);
    Country addCountry(String countryName);
}
