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
 * Servlet implementation class ProcesarPedido
 */
@WebServlet("/procesarPedido")
public class ProcesarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcesarPedido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		boolean stockSuperado = false;
		
		HashMap<LibroVO, Integer> carrito = (HashMap<LibroVO, Integer>)request.getSession().getAttribute("carrito");
		HashMap<Integer, String> stockSup = new HashMap<Integer, String>();
		
		if (request.getSession().getAttribute("usuario") == null) 
		{
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
		} else {
			
			if (carrito != null && !carrito.isEmpty()) {
                for (Map.Entry<LibroVO, Integer> entry : carrito.entrySet()) {
                    LibroVO producto = entry.getKey();
                    Integer cantidad = entry.getValue();
                    
                    //comprobar stock antes de procesar el pedido
                    if ( cantidad > LibroDAO.findByID(producto.getId()).getStock() ) 
                    {
                    	//se supera el stock
                    	//NO ESTOY MANDANDO ESTE MENSAJE A LA VISTA .JSP
                    	String mensaje = "Error: No hay stock suficiente";
    					stockSup.put(producto.getId(), mensaje);
                    	
                    	stockSuperado = true;
                    }
                    
                }
                if (stockSuperado) 
                {
                	request.setAttribute("stockSuperado", stockSup);
                	
                	request.getRequestDispatcher("views/carrito.jsp").forward(request, response);                	
                } else 
                {
                	// BIEEEN
                	request.getRequestDispatcher("views/procesarCompra.jsp").forward(request, response);
                }
                
			} else {
				// no hay productos en el carrito
				request.getRequestDispatcher("views/carrito.jsp").forward(request, response);
			}
			
			
			
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
