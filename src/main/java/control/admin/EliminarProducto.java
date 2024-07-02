package control.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.LibroDAO;
import model.VO.LibroVO;
import model.VO.UsuarioVO;

/**
 * Servlet implementation class EliminarProd
 */
@WebServlet("/eliminarProd")
public class EliminarProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarProducto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuarioA");
		
		if (usuario.getRol_id() != 3) {
			request.getRequestDispatcher("views/admin/home.jsp").forward(request, response);
		} else {
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			LibroVO libro = LibroDAO.findByID(id);
			libro.setBaja(true);
			
			LibroDAO.updateLibro(libro);
			
			response.sendRedirect(request.getContextPath()+"/productos");
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
