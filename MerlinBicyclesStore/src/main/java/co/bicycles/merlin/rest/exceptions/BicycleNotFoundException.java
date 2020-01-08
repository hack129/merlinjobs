package co.bicycles.merlin.rest.exceptions;

public class BicycleNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BicycleNotFoundException(String id) {
		super("Could not find bicycle " + id);
	}
}