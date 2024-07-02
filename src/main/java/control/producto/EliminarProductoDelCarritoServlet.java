package control.producto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.LibroDAO;
import model.VO.LibroVO;

/**
 * Servlet implementation class EliminarProductoDelCarritoServlet
 */
@WebServlet("/eliminarProducto")
public class EliminarProductoDelCarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarProductoDelCarritoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HashMap<LibroVO, Integer> carrito = (HashMap<LibroVO, Integer>)request.getSession().getAttribute("carrito");
		int cantidadTotal = (int)request.getSession().getAttribute("cantidadTotal");
		
		if (request.getParameter("id") != null && !request.getParameter("id").isEmpty())
		{
			int id = Integer.parseInt(request.getParameter("id"));
			if(carrito != null && carrito.containsKey(LibroDAO.findByID(id))) 
			{
				carrito.remove(LibroDAO.findByID(id));
				
			} else {
				
			}
		}
		
//		request.getRequestDispatcher("carrito.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath()+"/carrito");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
