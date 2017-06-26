package fr.epsi.adhesion;

import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Stateless
public class AdhesionRepository {
	
	@Resource(name = "adherentDataSource")
	private DataSource dataSource;
	
	@PersistenceContext(unitName="adherentPersistenceUnit")
	  private EntityManager entityManager;
	
	public void create(Adhesion adhesion, String confirmationMotDePasse) throws SQLException, ValidationException {
		adhesion.valider(confirmationMotDePasse);
		
			entityManager.persist(adhesion);
		}
	
	
	public List<Adhesion> getAll() throws SQLException {
		List<Adhesion> adhesions = new ArrayList<>();
		try (Connection connection = dataSource.getConnection();
		     Statement stmt = connection.createStatement();
		     ResultSet rs = stmt.executeQuery("select id, email, dateAdhesion from Adherent order by dateAdhesion")) {
			while (rs.next()) {
				Adhesion adhesion = toAdhesion(rs);
				adhesions.add(adhesion);
			}
		}
		return adhesions;
	}

	public Adhesion get(long id) throws SQLException {
		
		Adhesion adherent = entityManager.find(Adhesion.class, id);
		return adherent;
		
	}

	private Adhesion toAdhesion(ResultSet rs) throws SQLException {
		Adhesion adhesion = new Adhesion();
		adhesion.setId(rs.getLong("id"));
		adhesion.setEmail(rs.getString("email"));
		adhesion.setDateAdhesion(rs.getDate("dateAdhesion"));
		return adhesion;
	}

	public void delete(String email) throws SQLException {
		String sql = "delete from Adherent where email = ?";
		try (Connection connection = dataSource.getConnection();
		     PreparedStatement ptstmt = connection.prepareStatement(sql)) {
			ptstmt.setString(1, email);
			ptstmt.executeUpdate();
		}
	}


}
