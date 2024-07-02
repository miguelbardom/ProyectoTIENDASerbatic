package control.producto;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.LibroDAO;
import model.VO.LibroVO;

/**
 * Servlet implementation class ComprarServlet
 */
@WebServlet("/cantidad")
public class ModificarCantidadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarCantidadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
//		if(request.getParameter("id") != null)
//		{
			int id = Integer.parseInt(request.getParameter("id"));
			int cantidad = Integer.parseInt(request.getParameter("cantidad"));
			
			HashMap<LibroVO, Integer> carrito = (HashMap<LibroVO, Integer>)request.getSession().getAttribute("carrito");
			HashMap<Integer, String> stockSup = new HashMap<Integer, String>();
			
			if(carrito.containsKey(LibroDAO.findByID(id))) {
				
				if (cantidad<=0) {
					System.out.println("Error: cantidad negativa");
					String mensaje = "Error: No hay stock suficiente";
					stockSup.put(id, mensaje);
					request.setAttribute("stockSuperado", stockSup);
				} else {
					
					int cantidadActual = carrito.get(LibroDAO.findByID(id));
					//modificacion en el carrito
					carrito.put(LibroDAO.findByID(id), cantidad);
				}
				
				
				//modificacion en el catalogo
//				carrito.put(LibroDAO.findByID(id), cantidadActual + cantidad);
			} 
//			else {
//				carrito.put(LibroDAO.findByID(id), cantidad);			
//			}
//			System.out.println("id "+id);
//			System.out.println("cantidad "+cantidad);
			
//			request.setAttribute(, )
			
			System.out.println(carrito.size());
			System.out.println(carrito.toString());
			
//			request.getRequestDispatcher("carrito.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/carrito");
//		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
