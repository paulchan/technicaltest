package test.technical.spring.model;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String exception) {
		super(exception);
	}

}
