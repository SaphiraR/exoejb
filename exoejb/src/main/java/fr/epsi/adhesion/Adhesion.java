package fr.epsi.adhesion;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="adherent")
public class Adhesion {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Basic
	@Column(length = 100, nullable=false)
	private String email;
	
	@Basic
	@Column(length =50, nullable=false)
	private String motDePasse;
	
	 @Transient
	private boolean conditionsAcceptees;
	 
	 @Temporal(TemporalType.DATE)
	private Date dateAdhesion;

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public boolean isConditionsAcceptees() {
		return conditionsAcceptees;
	}

	public void setConditionsAcceptees(boolean conditionsAcceptees) {
		this.conditionsAcceptees = conditionsAcceptees;
	}

	public Date getDateAdhesion() {
		return dateAdhesion;
	}

	public void setDateAdhesion(Date dateAdhesion) {
		this.dateAdhesion = dateAdhesion;
	}

	public void valider(String confirmationMotDePasse) throws ValidationException {
		List<String> raisons = new ArrayList<>();
		
		if (this.email == null || "".equals(this.email)) {
			raisons.add("adhesion.email.vide");
		}
		else if (! this.email.contains("@")) {
			raisons.add("adhesion.email.invalide");
		}

		if (this.motDePasse == null || this.motDePasse.length() < 8) {
			raisons.add("adhesion.motDePasse.tropCourt");
		}
		
		if (confirmationMotDePasse == null || ! confirmationMotDePasse.equals(this.motDePasse)) {
			raisons.add("adhesion.motDePasse.confirmationIncorrecte");
		}

		if (! this.conditionsAcceptees) {
			raisons.add("adhesion.conditionsNonAcceptees");
		}
		
		if (! raisons.isEmpty()) {
			throw new ValidationException(raisons);
		}
		
		this.dateAdhesion = new Date();
	}
}
