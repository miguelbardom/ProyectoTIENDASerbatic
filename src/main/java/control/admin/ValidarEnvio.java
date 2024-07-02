package control.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.PedidoDAO;
import model.VO.PedidoVO;
import model.VO.UsuarioVO;

/**
 * Servlet implementation class ValidarEnvio
 */
@WebServlet("/validarEnvio")
public class ValidarEnvio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidarEnvio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuarioA");
		
		if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
			
			//buscar el pedido por el id
			PedidoVO pedido = PedidoDAO.findById(id);
			
			if (pedido != null) {
				
				pedido.setEstado("E");
				pedido.setNumfactura("");
				System.out.println(pedido);
				//actualizar estado pedido
				if (PedidoDAO.updatePedido(pedido)) {
					//pedido actualizado
					System.out.println("pedido actualizado");
					response.sendRedirect(request.getContextPath()+"/pedidos");
//					request.getRequestDispatcher("/historialPedidos").forward(request, response);
				} else {
					//fallo la actualización
					System.out.println("fallo la actualización");
//					response.sendRedirect(request.getContextPath()+"/historialPedidos");
					request.getRequestDispatcher("views/admin/pedidos.jsp").forward(request, response);
				}
				
			} else {
				//no existe el pedido
				System.out.println("no existe el pedido");
				request.getRequestDispatcher("views/admin/pedidos.jsp").forward(request, response);
			}
			
			
		} else {
			//no se ha introducido ningun id
			System.out.println("no se ha introducido ningun id");
			request.getRequestDispatcher("views/admin/pedidos.jsp").forward(request, response);
		}
		
		
	}

}
