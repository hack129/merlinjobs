package co.bicycles.merlin.rest.repository;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import co.bicycles.merlin.rest.exceptions.BicycleNotFoundException;
import co.bicycles.merlin.rest.model.Bicycle;

@Repository
public interface IBicycleRepository {
	
	public Bicycle getOne(String id) throws BicycleNotFoundException;
	public Collection<Bicycle> getAll();
	public Bicycle create(Bicycle bicycle);
	public Bicycle update(Bicycle bicycle);
	public Long delete(String id);
	
}
