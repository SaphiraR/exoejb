package fr.epsi.adhesion.api;

import fr.epsi.adhesion.Adhesion;

public class AdhesionAdapter {
	
	private String email;
	private String dateAdhesion;
	
	public AdhesionAdapter(Adhesion adhesion) {
		this.email = adhesion.getEmail();
		this.dateAdhesion = adhesion.getDateAdhesion().toString();
	}

	public String getEmail() {
		return email;
	}
	
	public String getDateAdhesion() {
		return dateAdhesion;
	}
}
