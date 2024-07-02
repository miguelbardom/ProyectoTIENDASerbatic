package control.producto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.LibroDAO;
import model.VO.LibroVO;

/**
 * Servlet implementation class MostrarCarritoServlet
 */
@WebServlet("/carrito")
public class MostrarCarritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarCarritoServlet() {
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
		int cantidadTotal = 0;
		Double precioTotal = 0.00;
		
		//crear
		// foreach de carrito
		//meter en una lista por cada findByID y añadir el numero de productos
//		request.setAttribute("carritoDetalle", carritoDetalle);
		
		//crear total € del carrito
		if (carrito != null && !carrito.isEmpty()) {
            for (Map.Entry<LibroVO, Integer> entry : carrito.entrySet()) {
                LibroVO producto = entry.getKey();
                Integer cantidad = entry.getValue();
                
                cantidadTotal += cantidad;
                precioTotal += producto.getPrecio() * cantidad;
                
            }
            //guardar en sesion
            request.getSession().setAttribute("cantidadTotal", cantidadTotal);
            request.getSession().setAttribute("precioTotal", precioTotal);
		} else {
			request.getSession().setAttribute("cantidadTotal", cantidadTotal);
		}
		
		request.getRequestDispatcher("views/carrito.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
