package co.bicycles.merlin.rest.controller;

import java.net.URI;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.bicycles.merlin.rest.exceptions.BicycleNotFoundException;
import co.bicycles.merlin.rest.model.Bicycle;
import co.bicycles.merlin.rest.repository.IBicycleRepository;

@RestController
public class BicyclesController {
	
	private static final String BICYCLES_RESOURCE_PATH = "/bicycles";

	@Autowired
	IBicycleRepository bicyclesRepository;
	
	@RequestMapping(path = BICYCLES_RESOURCE_PATH, method = RequestMethod.GET)
	public ResponseEntity<Object> getBicycle(@RequestParam(value="id") @NotBlank String id) {
		return findBicycle(id);
	}

	@RequestMapping(path = BICYCLES_RESOURCE_PATH + "/{id}", method = RequestMethod.GET)
	ResponseEntity<Object> findOneBicycle(@PathVariable @NotBlank String id)  {
		return findBicycle(id);
	}

	@RequestMapping(path = BICYCLES_RESOURCE_PATH, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addBicycle(@RequestBody Bicycle bicycle) 
	{
		Bicycle create = bicyclesRepository.create(bicycle);
		if(create !=null) {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/"+bicycle.getId())
					.buildAndExpand(1)
					.toUri();
			return ResponseEntity.created(location).build();
		} else {
			throw new ResponseStatusException(
					HttpStatus.CONFLICT, "Bicycle Exists");
		}
	}
	
	@RequestMapping(path = BICYCLES_RESOURCE_PATH,method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateBicycle(@RequestBody Bicycle bicycle) 
	{
		try {
			Bicycle update = bicyclesRepository.update(bicycle);
			return ResponseEntity.ok(update);
		} catch (BicycleNotFoundException e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Bicycle Not Found",e);
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error while Updating Bicycle",e);
		}
	}
	
	private ResponseEntity<Object> findBicycle(String id) {
		try {
			Bicycle bicycle = bicyclesRepository.getOne(id);
			return ResponseEntity.ok(bicycle);
		} catch (BicycleNotFoundException e) {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Bicycle Not Found",e);
		} catch (Exception e) {
			throw new ResponseStatusException(
			           HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error while finding Bicycle",e);
		}
	}
}