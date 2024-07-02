package control.producto;

import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
 * Servlet implementation class ProcesarPedido
 */
@WebServlet("/confirmarPedidoX")
public class ConfirmarPedidoX extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarPedidoX() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String metodopago = request.getParameter("metodoPago");
		
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
		Double precioTotal = (Double)request.getSession().getAttribute("precioTotal");
		HashMap<LibroVO, Integer> carrito = (HashMap<LibroVO, Integer>)request.getSession().getAttribute("carrito");
		
		HashMap<Integer, String> stockSup = new HashMap<Integer, String>();
		HashMap<Integer, DetalleVO> detalles = new HashMap<Integer, DetalleVO>();
		HashMap<Integer, LibroVO> productos = new HashMap<Integer, LibroVO>();
		
		if (!comprobarMetodoPago(metodopago)) 
		{
			request.getRequestDispatcher("views/procesarCompra.jsp").forward(request, response);
		} else 
		{
			if (!comprobarUsuario(usuario)) 
			{
				request.getRequestDispatcher("views/login.jsp").forward(request, response);
			} else 
			{
				if (!comprobarCarrito(carrito)) 
				{
					request.getRequestDispatcher("views/carrito.jsp").forward(request, response);
				} else 
				{
					if (!comprobarStock(carrito, stockSup)) 
					{
						request.setAttribute("stockSuperado", stockSup);
						
	                	request.getRequestDispatcher("views/carrito.jsp").forward(request, response);
					} else 
					{
						//crear pedido
						PedidoVO pedido = crearPedido(usuario, metodopago, precioTotal);
						
						if (pedido == null) 
						{
            				request.getRequestDispatcher("views/procesarCompra.jsp").forward(request, response);
						} else 
						{
							//guardar el pedido en la peticion
							request.setAttribute("pedido", pedido);
							// SACAR DE LA BASE DE DATOS
							
							if (!crearDetalles(carrito, pedido,detalles, productos)) 
							{
                				System.out.println("error al crear detalle");

                				borrarPedido();
                				
                				request.getRequestDispatcher("views/procesarCompra.jsp").forward(request, response);
							} else 
							{
								
								if (!restarStock(carrito)) 
								{
									borrarPedido();
	                				
	                				borrarDetalles(carrito);
	                				
					        		request.getRequestDispatcher("views/procesarCompra.jsp").forward(request, response);
								} else 
								{
									vaciarCarrito(carrito);
									
									//actualizar sesion cantidadtotal
									request.getSession().setAttribute("cantidadTotal",0);
									
									//guardar los detalles en la peticion
									request.setAttribute("detalles", detalles);
									
									//guardar los productos en la peticion
									request.setAttribute("productos", productos);
									
									// EXITOOOOO
									request.getRequestDispatcher("views/compraCompletada.jsp").forward(request, response);
								}
							}
						}
					}
				}
			}
		}
		
		
//		String vista = metodo(metodopago, usuario, carrito);
//		request.getRequestDispatcher(vista).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		
		
	}
	
	private boolean comprobarMetodoPago(String metodopago) {
		
		if (metodopago == null)	{
			//nulo
			System.out.println("metodopago es nulo o esta vacio");
			System.out.println(metodopago);
//			request.getRequestDispatcher("procesarCompra.jsp").forward(request, response);
			return false;
		} else if (!metodopago.equals("Tarjeta") && !metodopago.equals("Paypal")) {
			System.out.println(metodopago);
			System.out.println("metodo no disponible");
//			request.getRequestDispatcher("procesarCompra.jsp").forward(request, response);
			return false;
		} else {
			System.out.println(metodopago);
			return true;
		}
		
	}
	
	private boolean comprobarUsuario(UsuarioVO usuario) {
		
		if (usuario == null) {
			//redireccionar al carrito de nuevo, despues de logear
			return false;
		} else {
			return true;
		}
		
	}
	
	private boolean comprobarCarrito(HashMap<LibroVO, Integer> carrito) {
		if (carrito != null && !carrito.isEmpty()) {
			return true;
		} else {
			System.out.println("no hay productos en el carrito");
			return false;
		}
	}
	
	private boolean comprobarStock(HashMap<LibroVO, Integer> carrito, HashMap<Integer, String> stockSup) {
		
		boolean stockOK = false;
		
		for (Map.Entry<LibroVO, Integer> entry : carrito.entrySet()) {
			LibroVO producto = entry.getKey();
	        Integer cantidad = entry.getValue();
	        
	        LibroVO productoBD = LibroDAO.findByID(producto.getId());
	        int stockBD = productoBD.getStock();
	        
	        //comprobar stock antes de confimar el pedido
	        if ( cantidad > stockBD ) 
	        {
	        	//se supera el stock
	            //NO ESTOY MANDANDO ESTE MENSAJE A LA VISTA .JSP
	            String mensaje = "Error: No hay stock suficiente";
	    		stockSup.put(producto.getId(), mensaje);
	    		
	    		stockOK = false;
	        } else {
	            stockOK = true;
	        }
		}
		return stockOK;
	}
	
	private PedidoVO crearPedido(UsuarioVO usuario, String metodopago, Double precioTotal) {
		
		//crear pedido en bd tabla pedido
		        
		// Obtener la fecha actual
//	    LocalDate currentDate = LocalDate.now(); 
	       	// Convertir LocalDate a java.sql.Date
//	    Date fechaActual = Date.valueOf(currentDate);
	            		
        PedidoVO pedido = new PedidoVO();
		pedido.setId(0);
		pedido.setUsuario_id(usuario.getId());
//      pedido.setFecha(fechaActual);
		pedido.setFecha(new Date(System.currentTimeMillis()));
	    pedido.setMetodopago(metodopago);
//		pedido.setNumfactura(generarNumeroFactura(0));
		pedido.setTotal(precioTotal);
		pedido.setEstado("PE");
		
		System.out.println(pedido);
		
		if (PedidoDAO.crearPedido(pedido)) 
		{
			return pedido;
		} else 
		{
			System.out.println("error al crear pedido");
			return null;
		}
		
	}
	
	private boolean crearDetalles(HashMap<LibroVO, Integer> carrito, PedidoVO pedido, HashMap<Integer, DetalleVO> detalles, HashMap<Integer, LibroVO> productos) {
		
		boolean detallesCreado = false;
		
		//buscar en bd el id del pedido
		PedidoVO ultPedido = PedidoDAO.buscarUltimoPedido();
		
        for (Map.Entry<LibroVO, Integer> entry : carrito.entrySet()) {
          	LibroVO producto = entry.getKey();
	        Integer cantidad = entry.getValue();
	        
	        //crear detalles en bd tabla detalle
	        DetalleVO detalle = new DetalleVO(0,ultPedido.getId(),producto.getId(),cantidad,producto.getPrecio(),producto.getImpuesto(),(cantidad*producto.getPrecio()));
	                    
	        if (DetalleDAO.crearDetalle(detalle)) {
	        	detallesCreado = true;

	        	int ultimoIdDet = DetalleDAO.obtenerUltimoId();
	            detalles.put(ultimoIdDet, detalle);
	            
	            productos.put(producto.getId(), producto);
	        
	        } else {
	        	detallesCreado = false;
	        }
	    }
        return detallesCreado;
        
	}
	
	private boolean restarStock(HashMap<LibroVO, Integer> carrito) {
		
		boolean stockRestado = false;
		
		for (Map.Entry<LibroVO, Integer> entry : carrito.entrySet()) {
			LibroVO producto = entry.getKey();
	        Integer cantidad = entry.getValue();
		    //realizar compra:
		    //restar stock en la base de datos tabla producto
		    if (LibroDAO.restarStock(producto.getId(), cantidad)) 
		    {
		    	//stock actualizado
		    	System.out.println("stock actualizado");
		    	stockRestado = true;
		    } else 
		    {
				System.out.println("error al restar stock");
		    	stockRestado = false;
		    }
		}
		return stockRestado;
	}
	
	private void vaciarCarrito(HashMap<LibroVO, Integer> carrito) {
        
		//vaciar carrito
		carrito.clear();
	    
	}
	
	private void borrarPedido() {
		//buscar en bd el id del pedido
		PedidoVO ultPedido = PedidoDAO.buscarUltimoPedido();
		//borrar pedido creado
		PedidoDAO.deletePedido(ultPedido.getId());
	}
	
	private void borrarDetalles(HashMap<LibroVO, Integer> carrito) {
		//buscar en bd el id del pedido
		PedidoVO ultPedido = PedidoDAO.buscarUltimoPedido();
		
		for (Map.Entry<LibroVO, Integer> entry : carrito.entrySet()) {
          	LibroVO producto = entry.getKey();
	        Integer cantidad = entry.getValue();
	        
	        DetalleDAO.deleteDetalle(ultPedido.getId());
		}
	}

//			request.getRequestDispatcher("compraCompletada.jsp").forward(request, response);

	private String generarNumeroFactura(int id) {
        DecimalFormat df = new DecimalFormat("0000");
        String formattedId = df.format(id);
        
        return "FA-" + formattedId;
    }

}
