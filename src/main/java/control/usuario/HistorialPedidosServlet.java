package control.usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.PedidoDAO;
import model.DAO.ProductoDAO;
import model.VO.PedidoVO;
import model.VO.UsuarioVO;

/**
 * Servlet implementation class HistorialPedidosServlet
 */
@WebServlet("/historialPedidos")
public class HistorialPedidosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistorialPedidosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		
//		HashMap<String, String> estadosPed = new HashMap<String, String>();
		
		if (!comprobarUsuario(usuario)) {
			
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
			
		} else {
			
			List<PedidoVO> allPedidos = PedidoDAO.getAll();
			List<PedidoVO> pedidosUser = new ArrayList<PedidoVO>();
			
			for (PedidoVO pedido : allPedidos) {
				
				if (pedido.getUsuario_id() == usuario.getId()) {
					pedidosUser.add(pedido);
				}
				
			}
			
			request.setAttribute("pedidos", pedidosUser);
			
			request.getRequestDispatcher("views/historialPedidos.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}
	
	private boolean comprobarUsuario(UsuarioVO usuario) {
		
		if (usuario == null) {
			//redireccionar al carrito de nuevo, despues de logear
			return false;
		} else {
			return true;
		}
		
	}

}
