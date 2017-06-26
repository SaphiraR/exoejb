package fr.epsi.adhesion;

import java.util.List;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	private final List<String> raisons;

	public ValidationException(List<String> raisons) {
		this.raisons = raisons;
	}

	public List<String> getRaisons() {
		return raisons;
	}
}
