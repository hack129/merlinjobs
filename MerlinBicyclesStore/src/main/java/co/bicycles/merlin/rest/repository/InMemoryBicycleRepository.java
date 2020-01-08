package co.bicycles.merlin.rest.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Repository;

import co.bicycles.merlin.rest.exceptions.BicycleNotFoundException;
import co.bicycles.merlin.rest.model.Bicycle;

@Repository
public class InMemoryBicycleRepository implements IBicycleRepository {
	
	private ConcurrentHashMap<String, Bicycle> bicyclesStore = new ConcurrentHashMap<String, Bicycle>();
	
	public InMemoryBicycleRepository() {
		bicyclesStore = new ConcurrentHashMap<String, Bicycle>();
	}

	@Override
	public Bicycle getOne(String id) throws BicycleNotFoundException {
		Bicycle bicycle = bicyclesStore.get(id);
		if(bicycle == null) {
			throw new BicycleNotFoundException(id);
		} else {
			return bicycle;
		}
	}

	@Override
	public Collection<Bicycle> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bicycle create(Bicycle bicycle) {
		String generatedString = RandomStringUtils.randomAlphabetic(10);
		bicycle.setId(generatedString);
		bicyclesStore.put(generatedString, bicycle);
		return bicycle;
	}

	@Override
	public Bicycle update(Bicycle bicycle) {
		Bicycle bicycle2 = bicyclesStore.get(bicycle.getId());
		if(bicycle2 !=null ) {
			bicyclesStore.put(bicycle.getId(), bicycle);
			return bicycle;
		} else {
			throw new BicycleNotFoundException(bicycle.getId());
		}
	}

	@Override
	public Long delete(String id) {
		// TODO PENDIENTE
		return null;
	}

}
