package fr.epsi.adhesion;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adhesion")
public class AdhesionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AdhesionRepository adhesionRepository;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/adhesion/form.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Adhesion adhesion = creerAdhesion(req);
		String confirmationMotDePasse = req.getParameter("confirmationMotDePasse");
		req.setAttribute("adhesion", adhesion);

		try {
			adhesionRepository.create(adhesion, confirmationMotDePasse);
			getServletContext().getRequestDispatcher("/WEB-INF/adhesion/succes.jsp").forward(req, resp);
		} catch (ValidationException e) {
			Map<String, Boolean> mapRaisons = e.getRaisons().stream().collect(Collectors.toMap(Function.identity(), x->true));
			req.setAttribute("raisons", mapRaisons);
			doGet(req, resp);
		} catch (SQLException e) {
			log("Problème lors de la création d'une nouvelle adhésion", e);
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private Adhesion creerAdhesion(HttpServletRequest req) {
		Adhesion adhesion = new Adhesion();
		adhesion.setEmail(req.getParameter("email"));
		adhesion.setMotDePasse(req.getParameter("motDePasse"));
		adhesion.setConditionsAcceptees("on".equals(req.getParameter("conditionsAcceptees")));
		return adhesion;
	}

}
