package control.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.DAO.CategoriaDAO;
import model.DAO.LibroDAO;
import model.VO.LibroVO;
import model.VO.UsuarioVO;
import model.VO.CategoriaVO;

/**
 * Servlet implementation class CrearProductoServlet
 */
@WebServlet("/crearProducto")
public class CrearProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearProductoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		UsuarioVO usuario = (UsuarioVO)request.getSession().getAttribute("usuarioA");
		 
		if (usuario.getRol_id() != 2 && usuario.getRol_id() != 3) {
			request.getRequestDispatcher("views/index.jsp").forward(request, response);
		} else {
			
//			int id = Integer.parseInt(request.getParameter("id"));
//			
//			LibroVO libro = LibroDAO.findByID(id);
//			System.out.println(libro);
			
			//recuperar los datos (libros)
//			request.setAttribute("libro", libro);
			
			List<CategoriaVO> categorias = new ArrayList<>();
			categorias = CategoriaDAO.getAll();
			request.setAttribute("categorias", categorias);
			
			request.getRequestDispatcher("views/admin/nuevoProducto.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		
		if (ServletFileUpload.isMultipartContent(request)) {
		    try {
		        // Crear un manejador de carga de archivos
		        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

		        // Parsear la solicitud y obtener una lista de elementos de archivo
		        List<FileItem> items = upload.parseRequest(request);

		        // Declarar variables para los campos del formulario
		        String titulo = null;
		        String autor = null;
		        String editorial = null;
		        String isbn = null;
		        int categoriaId = 0;
		        double precio = 0.0;
		        double impuesto = 0.0;
		        int stock = 0;
		        String img = null;
		        boolean baja = false;
		        String descripcion = null;
		        String formato = null;
		        int paginas = 0;
		        int anoPublicacion = 0;
		        
		        // Procesar cada elemento de archivo
		        for (FileItem item : items) {
		            if (item.isFormField()) {
		                // Es un campo de formulario regular
		                String fieldName = item.getFieldName();
		                String fieldValue = item.getString("UTF-8");
		                
//		                System.out.println(fieldValue);

		                // Asignar valor a la variable correspondiente
		                switch (fieldName) {
		                    case "titulo":
		                        titulo = fieldValue;
		                        break;
		                    case "autor":
		                        autor = fieldValue;
		                        break;
		                    case "editorial":
		                        editorial = fieldValue;
		                        break;
		                    case "isbn":
		                        isbn = fieldValue;
		                        break;
		                    case "categoria_id":
		                        categoriaId = Integer.parseInt(fieldValue);
		                        break;
		                    case "precio":
		                        precio = Double.parseDouble(fieldValue);
		                        break;
		                    case "impuesto":
		                        impuesto = Double.parseDouble(fieldValue);
		                        break;
		                    case "stock":
		                        stock = Integer.parseInt(fieldValue);
		                        break;
		                    case "baja":
		                        baja = Boolean.parseBoolean(fieldValue);
		                        break;
		                    case "descripcion":
		                        descripcion = fieldValue;
		                        break;
		                    case "formato":
		                        formato = fieldValue;
		                        break;
		                    case "paginas":
		                        paginas = Integer.parseInt(fieldValue);
		                        break;
		                    case "ano_publicacion":
		                        anoPublicacion = Integer.parseInt(fieldValue);
		                        break;
		                    default:
		                        // Otro campo de formulario (no se maneja en este ejemplo)
		                        break;
		                }
		            } else {
		            	if (item!=null) {
			                // Es un campo de archivo (img en este caso)
			                String fileName = new File(item.getName()).getName();
			                String filePath = "C:/Users/Miguel/OneDrive - Grupo VASS/FCT-Serbatic/CURSO JAVA/WEB/"
			                		+ "ProyectoTIENDASerbatic/src/main/webapp/img/" + fileName;
	
			                // Guardar el archivo en el servidor
			                item.write(new File(filePath));
	
			                // Asignar la ruta del archivo a la variable img
			                img = "/img/"+fileName;
		            	}
		            }
		        }

		        // Crear un objeto LibroVO con los datos obtenidos
		        LibroVO libro = new LibroVO(0, titulo, autor, editorial, isbn, categoriaId, precio, impuesto, stock,
		                img, baja, descripcion, formato, paginas, anoPublicacion);

		        // Guardar el libro en la base de datos usando LibroDAO
		        LibroDAO.crearLibro(libro);
		        
		        System.out.println(libro);

		        // Redireccionar a la página de productos después de guardar exitosamente
//		        request.getRequestDispatcher("views/admin/productos.jsp").forward(request, response);
		        response.sendRedirect(request.getContextPath()+"/productos");

		    } catch (Exception e) {
		        e.printStackTrace();
		        // Manejar la excepción si ocurre algún error durante el procesamiento
		        request.getRequestDispatcher("views/admin/nuevoProducto.jsp").forward(request, response);
		    }
		} else {
		    // La solicitud no es multipart (no es un formulario de carga de archivos)
		    request.getRequestDispatcher("views/admin/nuevoProducto.jsp").forward(request, response);
		}

		
	}

}
