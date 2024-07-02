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

import model.DAO.DetalleDAO;
import model.DAO.PedidoDAO;
import model.DAO.LibroDAO;
import model.VO.DetalleVO;
import model.VO.PedidoVO;
import model.VO.LibroVO;
import model.VO.UsuarioVO;

/**
 * Servlet implementation class DetalleServlet
 */
@WebServlet("/detalle")
public class DetalleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetalleServlet() {
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
		
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		
//		HashMap<Integer, DetalleVO> detalles = new HashMap<Integer, DetalleVO>();
//		HashMap<Integer, LibroVO> productos = new HashMap<Integer, LibroVO>();
		
		List<DetalleVO> detalles = new ArrayList<DetalleVO>();
		List<LibroVO> productos = new ArrayList<LibroVO>();
		
		if (!comprobarUsuario(usuario)) {
			
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
			
		} else {
			
			if (request.getParameter("id") != null && !request.getParameter("id").isEmpty()) {
				
				//buscar el pedido por el id
				PedidoVO pedido = PedidoDAO.findById(id);
				System.out.println(pedido);
				
				if (pedido != null) {
					//guardar pedido en la peticion
					request.setAttribute("pedido", pedido);
					
					//buscar detalles por pedido_id
					detalles = DetalleDAO.getAllByPedidoId(id);
					request.setAttribute("detalles", detalles);
					
					for (DetalleVO detalle : detalles) {
						
						int idPro = detalle.getLibro_id();
						LibroVO producto = LibroDAO.findByID(idPro);
						
//						if (producto == null) {
//						}
						System.out.println(detalle);
						System.out.println(producto);
						productos.add(producto);
					}
					request.setAttribute("productos", productos);
					request.getRequestDispatcher("views/detalle.jsp").forward(request, response);
					
				} else {
					//no existe el pedido
					System.out.println("no existe el pedido");
					request.getRequestDispatcher("views/historialPedidos.jsp").forward(request, response);
				}
				
			} else {
				//no se ha introducido ningun id
				System.out.println("no se ha introducido ningun id");
				request.getRequestDispatcher("views/historialPedidos.jsp").forward(request, response);
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
	
	private boolean comprobarUsuario(UsuarioVO usuario) {
		
		if (usuario == null) {
			//redireccionar al carrito de nuevo, despues de logear
			return false;
		} else {
			return true;
		}
		
	}


}
