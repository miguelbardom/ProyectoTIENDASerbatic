package control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import control.log.Log;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import model.DAO.LibroDAO;
//import model.DAO.ProductoDAO;
import model.VO.LibroVO;
//import model.VO.ProductoVO;
import service.LibroService;

/**
 * Servlet implementation class EntradaServlet
 */

@WebServlet("")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(IndexServlet.class);
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
//		System.setProperty("log4j.configurationFile", "classpath:log4j/log4j.properties");
//		String ruta = "C:/Users/Miguel/OneDrive - Grupo VASS/FCT-Serbatic/CURSO JAVA/WEB/ProyectoTIENDASerbatic/src/main/resources/log4j/log4j.properties";
//		PropertyConfigurator.configure(ruta);
		Log.log();
		
		HashMap<String, String> estadosPed = new HashMap<String, String>();
		estadosPed.put("PE", "Pendiente de envío");
		estadosPed.put("PC", "Pendiente de cancelación");
		estadosPed.put("E", "Enviado");
		estadosPed.put("C", "Cancelado");
		request.getSession().setAttribute("estadosPed", estadosPed);
		
		String pagina = "views/index.jsp";
		
		if(request.getSession().getAttribute("carrito") == null) {
			request.getSession().setAttribute("carrito", new HashMap<LibroVO, Integer>());
			System.out.println("Carrito disponible");
			logger.info("carrito disponible");
			
			HashMap<LibroVO, Integer> carrito = (HashMap<LibroVO, Integer>)request.getSession().getAttribute("carrito");
			int cantidadTotal = 0;
			for (Map.Entry<LibroVO, Integer> entry : carrito.entrySet()) {
				LibroVO libro = entry.getKey();
                Integer cantidad = entry.getValue();
                
                cantidadTotal += cantidad;
            }
            //guardar en sesion
            request.getSession().setAttribute("cantidadTotal", cantidadTotal);
            System.out.println("cantidadTotal: "+cantidadTotal);
			
		}
		
		//recuperar los datos (libros)
		request.setAttribute("catalogo", LibroDAO.findAll());
		logger.info("catalogo disponible");
//		logger.error("prueba de error");
		
		//recuperar los datos (libros)
		List<LibroVO> masVendidos = LibroService.obtenerTresLibrosMenorStock();
		request.setAttribute("masVendidos", masVendidos);
		logger.info("mas vendidos disponible");
		
		//recuperar las editoriales
		List<String> editoriales = LibroDAO.obtenerEditorialesUnicas();
		request.setAttribute("editoriales", editoriales);
		logger.info("editoriales disponible");
		
		// i18n
		String idioma = request.getParameter("idioma");
		
		System.out.println(idioma);
		
		if (idioma == null || (!idioma.equals("es") && !idioma.equals("en"))) {
		    // Si el idioma es nulo o no es válido, establecerlo como "es" (español) por defecto
		    idioma = "es";
		}
		
		System.out.println(idioma);
		request.getSession().setAttribute("idioma", idioma);
		
        Locale locale = new Locale(idioma);
        
        // Carga el archivo de propiedades correspondiente
        ResourceBundle menu = ResourceBundle.getBundle("i18n/menu", locale);
        
        String libros = menu.getString("libros");
        String categorias = menu.getString("categorias");
        String contacto = menu.getString("contacto");
        String lang = menu.getString("lang");
        String login = menu.getString("login");
        String registro = menu.getString("registro");
        String cuenta = menu.getString("cuenta");
        String logout = menu.getString("logout");
        String c1 = menu.getString("c1");
        String c2 = menu.getString("c2");
        String c3 = menu.getString("c3");
        String c4 = menu.getString("c4");
        
        request.getSession().setAttribute("libros", libros);
        request.getSession().setAttribute("categorias", categorias);
        request.getSession().setAttribute("contacto", contacto);
        request.getSession().setAttribute("lang", lang);
        request.getSession().setAttribute("login", login);
        request.getSession().setAttribute("registro", registro);
        request.getSession().setAttribute("cuenta", cuenta);
        request.getSession().setAttribute("logout", logout);
        request.getSession().setAttribute("c1", c1);
        request.getSession().setAttribute("c2", c2);
        request.getSession().setAttribute("c3", c3);
        request.getSession().setAttribute("c4", c4);
		
		
		request.getRequestDispatcher(pagina).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
	}

}
