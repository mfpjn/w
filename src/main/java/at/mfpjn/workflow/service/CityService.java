package at.mfpjn.workflow.service;

import at.mfpjn.workflow.dao.CityDao;
import at.mfpjn.workflow.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cityService")
public class CityService {

	@Autowired
	private CityDao cityDao;

	public List<City> getCities() {
		return cityDao.getCities();
	}

	public City getCity(int cityid) {
		return cityDao.getCity(cityid);
	}
	
	public void updateCity(City city) {
		cityDao.updateCity(city);
	}
}
