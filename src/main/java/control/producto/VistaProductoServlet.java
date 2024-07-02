package control.producto;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.LibroDAO;
import model.DAO.ProductoDAO;
import model.VO.LibroVO;
import model.VO.ProductoVO;

/**
 * Servlet implementation class VistaProductoServlet
 */
@WebServlet("/producto")
public class VistaProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VistaProductoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) 
		{
			LibroVO producto = LibroDAO.findByID(id);
			
			if (producto != null) 
			{
				request.setAttribute("producto", producto);
				
				request.getRequestDispatcher("views/producto.jsp").forward(request, response);
			} else 
			{
				//no existe el id
				request.getRequestDispatcher("views/index.jsp").forward(request, response);
			}
			
		} else {
			//no se ha introducido ningun id
			request.getRequestDispatcher("views/index.jsp").forward(request, response);
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
