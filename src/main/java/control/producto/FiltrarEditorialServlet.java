package control.producto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import control.IndexServlet;
import control.log.Log;
import control.usuario.LoginServlet;
import model.DAO.LibroDAO;
import model.DAO.ProductoDAO;
import model.VO.LibroVO;
import model.VO.ProductoVO;

/**
 * Servlet implementation class ComprarServlet
 */
@WebServlet("/editorial")
public class FiltrarEditorialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(IndexServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltrarEditorialServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
//		Log.log();
		
		// Obtener parámetros de precio mínimo y máximo del request
        String editorial = (String)request.getParameter("editorial");

        // Obtener productos filtrados por el rango de precios
        List<LibroVO> libros = LibroDAO.findAll();
        List<LibroVO> librosEdito = new ArrayList();
        for (LibroVO libro : libros) {
        	if (libro.getEditorial().equals(editorial)) {
        		
        		librosEdito.add(libro);
        	}
        }
        if (request.getParameter("editorial").equals("0")) {
        	request.setAttribute("librosEdito", libros);
        } else {
        	
        	// Guardar productos en el request como atributo para mostrar en la vista
        	request.setAttribute("librosEdito", librosEdito);
        }
        request.setAttribute("editorial", editorial);

        // Redirigir a la página de visualización de productos
//        request.getRequestDispatcher("views/index.jsp").forward(request, response);
		
			
		String idioma = (String) request.getSession().getAttribute("idioma");
		request.getRequestDispatcher("/?idioma="+idioma).forward(request, response);
			
//			response.sendRedirect(request.getContextPath()+"/?idioma="+idioma);
			
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		
		
		
	}

}
