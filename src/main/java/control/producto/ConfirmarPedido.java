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
@WebServlet("/confirmarPedido")
public class ConfirmarPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmarPedido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String metodopago = "";
		if (request.getParameter("metodoPago") != null) 
		{
			metodopago = request.getParameter("metodoPago");
			System.out.println(metodopago);
		}
		
		boolean stockSuperado = false;
		boolean stockRestado = false;
		boolean pedidoCreado = false;
		boolean detallesCreado = false;
		
		HashMap<LibroVO, Integer> carrito = (HashMap<LibroVO, Integer>)request.getSession().getAttribute("carrito");
		HashMap<Integer, String> stockSup = new HashMap<Integer, String>();
		HashMap<Integer, DetalleVO> detalles = new HashMap<Integer, DetalleVO>();
		HashMap<Integer, LibroVO> productos = new HashMap<Integer, LibroVO>();
		
		if (request.getSession().getAttribute("usuario") == null) 
		{
			//redireccionar al carrito de nuevo, despues de logear
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
		} else {
			
//			if (metodopago != null && !metodopago.isEmpty()) {
				
				UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuario");
				
				if (carrito != null && !carrito.isEmpty()) {
					
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
	    					
	    					stockSuperado = true;
	    					
	                    } else {
	                    	
	                    	//realizar compra:
	                    		//restar stock en la base de datos tabla producto
	                    	if (LibroDAO.restarStock(producto.getId(), cantidad)) 
	                    	{
	                    		//stock actualizado
	                    		System.out.println("stock actualizado");
	                    		stockRestado = true;
	                    	} else {
	                    		stockRestado = false;
	                    	}
	                    	
	                    }
	                    
	                }
					
					if (stockSuperado) {
						request.setAttribute("stockSuperado", stockSup);
						
	                	request.getRequestDispatcher("views/carrito.jsp").forward(request, response);
	                	
					} else 
					{
						if (stockRestado) 
						{
							
							//crear pedido en bd tabla pedido
		                		//obtener id ultimo registro tabla
		                    		// no sé si es la mejor manera
		                    		// problema si se elimina un registro
		                    		// en ese caso habria que insertar primero y luego update
		                	int ultimoIdPed = PedidoDAO.obtenerUltimoId();
		                	
		                	// HACER A TRAVÉS DEL NUMERO DE FACTURA!!!
		            		
		                		// Obtener la fecha actual
	//	            		LocalDate currentDate = LocalDate.now(); 
		            			// Convertir LocalDate a java.sql.Date
	//	            		Date fechaActual = Date.valueOf(currentDate);
		            		
		            		PedidoVO pedido = new PedidoVO();
		            		pedido.setId(ultimoIdPed+1);
		            		pedido.setUsuario_id(usuario.getId());
	//            			pedido.setFecha(fechaActual);
		            		pedido.setFecha(new Date(System.currentTimeMillis()));
	            			pedido.setMetodopago(metodopago);
		            		pedido.setNumfactura(generarNumeroFactura(ultimoIdPed+1));
		            		pedido.setTotal( (Double)request.getSession().getAttribute("precioTotal") );
		            		
		            		System.out.println(pedido);
		            		
		            		if (PedidoDAO.crearPedido(pedido)) 
	                		{
	                			pedidoCreado = true;
	                			
            					//guardar el pedido en la peticion
            					request.setAttribute("pedido", pedido);
	                			
	                			for (Map.Entry<LibroVO, Integer> entry : carrito.entrySet()) {
	                                LibroVO producto = entry.getKey();
	                                Integer cantidad = entry.getValue();
	                                
	                                //crear detalles en bd tabla detalle
	                                DetalleVO detalle = new DetalleVO(0,pedido.getId(),producto.getId(),cantidad,producto.getPrecio(),producto.getImpuesto(),(cantidad*producto.getPrecio()));
	                                
	                                if (DetalleDAO.crearDetalle(detalle)) {
	                                	
	                                	detallesCreado = true;
	                                	
	                                	int ultimoIdDet = DetalleDAO.obtenerUltimoId();
	                                	detalles.put(ultimoIdDet+1, detalle);
	                                	
	                                	productos.put(producto.getId(), producto);
	                                	
	                                } else {
	                                	detallesCreado = false;
	                                }
	                			}
	                			
	                			if (detallesCreado) 
	                			{
	                				//actualizar sesion cantidadtotal
	                				request.getSession().setAttribute("cantidadTotal",0);
	                				
	                				//vaciar carrito
	            					carrito.clear();
	                				
	            					//guardar los detalles en la peticion
	            					request.setAttribute("detalles", detalles);
	            					
	            					//guardar los productos en la peticion
	            					request.setAttribute("productos", productos);
	            					
	                				// EXITOOOOO
	                				request.getRequestDispatcher("views/compraCompletada.jsp").forward(request, response);
	                				
	                			} else {
	                				System.out.println("error al crear detalle");
	                				request.getRequestDispatcher("views/procesarCompra.jsp").forward(request, response);
	                			}
	                			
	                		} else {
	                			System.out.println("error al crear pedido");
	            				request.getRequestDispatcher("views/procesarCompra.jsp").forward(request, response);
	                		}
							
						} else {
							System.out.println("error al restar stock");
            				request.getRequestDispatcher("views/procesarCompra.jsp").forward(request, response);
						}
						
					}
					
				} else {
					System.out.println("no hay productos en el carrito");
					request.getRequestDispatcher("views/carrito.jsp").forward(request, response);
				}
			
//			} else {
//				System.out.println("metodopago es nulo o esta vacio");
//				System.out.println(metodopago);
//				request.getRequestDispatcher("procesarCompra.jsp").forward(request, response);
//			}
						
//			request.getRequestDispatcher("compraCompletada.jsp").forward(request, response);
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		
		
	}
	
	private String generarNumeroFactura(int id) {
        DecimalFormat df = new DecimalFormat("0000");
        String formattedId = df.format(id);
        
        return "FA-" + formattedId;
    }

}
