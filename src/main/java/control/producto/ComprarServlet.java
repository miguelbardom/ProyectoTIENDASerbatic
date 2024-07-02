package control.producto;

import java.io.IOException;
import java.util.HashMap;
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
@WebServlet("/comprar")
public class ComprarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(IndexServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprarServlet() {
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
		
		
//		if(request.getParameter("id") != null)
//		{
			int id = Integer.parseInt(request.getParameter("id"));
			int cantidad = Integer.parseInt(request.getParameter("cantidad"));
			
			HashMap<LibroVO, Integer> carrito = (HashMap<LibroVO, Integer>)request.getSession().getAttribute("carrito");
			int cantidadTotal = 0;
			HashMap<Integer, String> stockSup = new HashMap<Integer, String>();
			
			if(carrito.containsKey(LibroDAO.findByID(id))) {
				int cantidadActual = carrito.get(LibroDAO.findByID(id));
				//modificacion en el carrito
//				carrito.put(LibroDAO.findByID(id), cantidad);
				
				//modificacion en el catalogo
				if ((cantidadActual + cantidad) > LibroDAO.findByID(id).getStock()) 
				{
					System.out.println("Error: La cantidad sumada a la cantidad existente en el carrito supera el stock actual");
					String mensaje = "Error: No hay stock suficiente";
					logger.error("Error: No hay stock suficiente");
					stockSup.put(id, mensaje);
					request.setAttribute("stockSuperado", stockSup);
					
				} else if (cantidad<=0) {
					System.out.println("Error: cantidad negativa");
					String mensaje = "Error: No hay stock suficiente";
					logger.error("Error: cantidad negativa");
					stockSup.put(id, mensaje);
					request.setAttribute("stockSuperado", stockSup);
				} else {
					logger.info("Producto añadido al carrito");
					carrito.put(LibroDAO.findByID(id), cantidadActual + cantidad);
				}
			} else {
				logger.info("Producto añadido al carrito");
				carrito.put(LibroDAO.findByID(id), cantidad);			
			}
//			System.out.println("id "+id);
//			System.out.println("cantidad "+cantidad);
			
//			request.setAttribute(, )
			
			for (Map.Entry<LibroVO, Integer> entry : carrito.entrySet()) {
				LibroVO producto = entry.getKey();
                Integer cantidadP = entry.getValue();
                
                cantidadTotal += cantidadP;
                
            }
            //guardar en sesion
            request.getSession().setAttribute("cantidadTotal", cantidadTotal);
			
//			System.out.println(carrito.size());
			System.out.println(carrito.toString());
			
			String vistaOrigen = request.getParameter("vista");
			String categoria_id = request.getParameter("categoria_id");
			
			String idioma = (String) request.getSession().getAttribute("idioma");
			
//			response.sendRedirect(request.getContextPath()+"/?idioma="+idioma);
			
			if (vistaOrigen.equals("index")) 
			{
				request.getRequestDispatcher("/?idioma="+idioma).forward(request, response);
				
			} else if (vistaOrigen.equals("producto")) 
			{
				request.getRequestDispatcher("/producto").forward(request, response);
				
			} 
			else if (vistaOrigen.equals("categoria")) {
				request.getRequestDispatcher("/categoria?id="+categoria_id).forward(request, response);
			}
			
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
