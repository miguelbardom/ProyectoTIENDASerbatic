package control.admin;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.LibroDAO;
import model.DAO.PedidoDAO;

/**
 * Servlet implementation class PedidosServlet
 */
@WebServlet("/pedidos")
public class PedidosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PedidosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HashMap<String, String> estadosPed = new HashMap<String, String>();
		estadosPed.put("PE", "Pendiente de envío");
		estadosPed.put("PC", "Pendiente de cancelación");
		estadosPed.put("E", "Enviado");
		estadosPed.put("C", "Cancelado");
		request.getSession().setAttribute("estadosPed", estadosPed);
		
		//recuperar los pedidos
		request.setAttribute("pedidos", PedidoDAO.getAll());
				
		request.getRequestDispatcher("views/admin/pedidos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
