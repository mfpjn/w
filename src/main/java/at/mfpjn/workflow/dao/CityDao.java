package at.mfpjn.workflow.dao;

import at.mfpjn.workflow.model.City;

import java.util.List;


public interface CityDao {
	
	List<City> getCities();
	
	City getCity(int id);
	
	void updateCity(City city);

	void insertCity(City city);
	
	City getCityByName(String city);

}
