package fr.epsi.adhesion;

import java.io.IOException;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adhesions")
public class AdhesionListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AdhesionRepository adhesionRepository;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setAttribute("adhesions", adhesionRepository.getAll());
			getServletContext().getRequestDispatcher("/WEB-INF/adhesion/list.jsp").forward(req, resp);
		} catch (SQLException e) {
			log("Problème lors de la récupération de la liste des adhérents", e);
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
