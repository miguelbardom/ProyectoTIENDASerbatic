package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import model.VO.LibroVO;

public class LibroDAO {

    public static List<LibroVO> findAll() {
        ArrayList<LibroVO> lista = new ArrayList<>();
        Connection con = Conexion.getConexion();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM libro WHERE baja = false");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LibroVO libro = new LibroVO();
                
                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setCategoria_id(rs.getInt("categoria_id"));
                libro.setPrecio(rs.getDouble("precio"));
                libro.setImpuesto(rs.getDouble("impuesto"));
                libro.setStock(rs.getInt("stock"));
                libro.setUrl(rs.getString("url"));
                libro.setBaja(rs.getBoolean("baja"));
                libro.setDescripcion(rs.getString("descripcion"));
                libro.setFormato(rs.getString("formato"));
                libro.setPaginas(rs.getInt("paginas"));
                libro.setAño_publicacion(rs.getInt("año_publicacion"));

                lista.add(libro);
            }

            return lista;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        Conexion.desconectar();
        return lista;
    }

    public static LibroVO findByID(int id) {
        Connection con = Conexion.getConexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        LibroVO libro = null;

        try {
            ps = con.prepareStatement("SELECT * FROM libro WHERE id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                libro = new LibroVO();

                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setCategoria_id(rs.getInt("categoria_id"));
                libro.setPrecio(rs.getDouble("precio"));
                libro.setImpuesto(rs.getDouble("impuesto"));
                libro.setStock(rs.getInt("stock"));
                libro.setUrl(rs.getString("url"));
                libro.setBaja(rs.getBoolean("baja"));
                libro.setDescripcion(rs.getString("descripcion"));
                libro.setFormato(rs.getString("formato"));
                libro.setPaginas(rs.getInt("paginas"));
                libro.setAño_publicacion(rs.getInt("año_publicacion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        Conexion.desconectar();
        return libro;
    }
    
    public static boolean crearLibro(LibroVO libro) {
		Connection con = null;
		PreparedStatement ps = null;
		boolean success = false;

		try {
			con = Conexion.getConexion();
			con.setAutoCommit(false); // Habilitar el modo de transacción explícita

			String sql = "INSERT INTO libro (titulo,autor,editorial,isbn,categoria_id,precio,impuesto,stock,"
					+ "url,baja,descripcion,formato,paginas,año_publicacion) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);
	        ps.setString(1, libro.getTitulo());
	        ps.setString(2, libro.getAutor());
	        ps.setString(3, libro.getEditorial());
	        ps.setString(4, libro.getIsbn());
	        ps.setInt(5, libro.getCategoria_id());
	        ps.setDouble(6, libro.getPrecio());
	        ps.setDouble(7, libro.getImpuesto());
	        ps.setInt(8, libro.getStock());
	        ps.setString(9, libro.getUrl());
	        ps.setBoolean(10, libro.isBaja());
	        ps.setString(11, libro.getDescripcion());
	        ps.setString(12, libro.getFormato());
	        ps.setInt(13, libro.getPaginas());
	        ps.setInt(14, libro.getAño_publicacion());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				success = true;
				con.commit(); // Confirmar la transacción
			} else {
				con.rollback(); // Revertir la transacción si no se realizó la inserción
			}

		} catch (SQLException e) {
			e.printStackTrace();
			if (con != null) {
                try {
                    con.rollback(); // Revertir la transacción en caso de error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
		} 
		Conexion.desconectar();
		return success;
	}
    
    public static boolean updateLibro(LibroVO libro) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean success = false;

        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false); // Habilitar el modo de transacción explícita

            String sql = "UPDATE libro SET titulo=?, autor=?, editorial=?, isbn=?, categoria_id=?, " +
                         "precio=?, impuesto=?, stock=?, url=?, baja=?, descripcion=?, formato=?, " +
                         "paginas=?, año_publicacion=? WHERE id=?";

            ps = con.prepareStatement(sql);
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getEditorial());
            ps.setString(4, libro.getIsbn());
            ps.setInt(5, libro.getCategoria_id());
            ps.setDouble(6, libro.getPrecio());
            ps.setDouble(7, libro.getImpuesto());
            ps.setInt(8, libro.getStock());
            ps.setString(9, libro.getUrl());
            ps.setBoolean(10, libro.isBaja());
            ps.setString(11, libro.getDescripcion());
            ps.setString(12, libro.getFormato());
            ps.setInt(13, libro.getPaginas());
            ps.setInt(14, libro.getAño_publicacion());
            ps.setInt(15, libro.getId()); // ID del libro a actualizar

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                success = true;
                con.commit(); // Confirmar la transacción
            } else {
                con.rollback(); // Revertir la transacción si no se realizó la actualización
            }

        } catch (SQLException e) {
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback(); // Revertir la transacción en caso de error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        Conexion.desconectar(); // Cerrar la conexión
        return success;
    }
    
    
    
    public static boolean deleteDetalle(int id) {
		
		boolean success = false;
		
		Connection conexion = Conexion.getConexion();
        String sql = "DELETE FROM libro WHERE id=?";
        
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
				success = true;
				conexion.commit(); // Confirmar la transacción
			} else {
				conexion.rollback(); // Revertir la transacción si no se realizó la inserción
			}
            
        } catch (SQLException e) {
            e.printStackTrace();
            success = false;
        }
        Conexion.desconectar();
        return success;
    }
    
    public static boolean restarStock(int id, int cantidad) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = Conexion.getConexion();

            // Verificar el libro actual en la base de datos
            LibroVO producto = findByID(id);
            if (producto == null) {
                System.out.println("Libro no encontrado con ID: " + id);
                return false;
            }

            int stockActual = producto.getStock();

            // Verificar si hay suficiente stock para restar la cantidad solicitada
            if (stockActual < cantidad) {
                System.out.println("No hay suficiente stock para restar " + cantidad + " unidades del libro con ID: " + id);
                return false;
            }

            // Calcular el nuevo stock después de restar la cantidad
            int nuevoStock = stockActual - cantidad;

            // Actualizar el stock en la base de datos
            ps = con.prepareStatement("UPDATE libro SET stock = ? WHERE id = ?");
            ps.setInt(1, nuevoStock);
            ps.setInt(2, id);
            int rowsAffected = ps.executeUpdate();

            // Confirmar la transacción
            con.commit();

            return rowsAffected > 0;
        } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            con.rollback(); // Revertir la transacción en caso de error
	        } catch (SQLException ex) {
                ex.printStackTrace();
	        }
	        
	        return false;
	    }
    }

	public static List<LibroVO> getLibrosPorPrecio(double precioMin, double precioMax) {
		List<LibroVO> lista = new ArrayList<>();
        Connection con = Conexion.getConexion();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM libro WHERE precio BETWEEN ? AND ?");
            ps.setDouble(1, precioMin);
            ps.setDouble(2, precioMax);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            	LibroVO libro = new LibroVO();
            	
                // Asignar atributos del producto desde el ResultSet
            	libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setEditorial(rs.getString("editorial"));
                libro.setIsbn(rs.getString("isbn"));
                libro.setCategoria_id(rs.getInt("categoria_id"));
                libro.setPrecio(rs.getDouble("precio"));
                libro.setImpuesto(rs.getDouble("impuesto"));
                libro.setStock(rs.getInt("stock"));
                libro.setUrl(rs.getString("url"));
                libro.setBaja(rs.getBoolean("baja"));
                libro.setDescripcion(rs.getString("descripcion"));
                libro.setFormato(rs.getString("formato"));
                libro.setPaginas(rs.getInt("paginas"));
                libro.setAño_publicacion(rs.getInt("año_publicacion"));
                
                // Agregar producto a la lista de productos
                lista.add(libro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.desconectar();
        }

        return lista;
	}
	
	// Método para obtener todas las editoriales de los libros sin duplicados
    public static List<String> obtenerEditorialesUnicas() {
    	List<String> editorialesUnicas = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConexion();
            ps = con.prepareStatement("SELECT DISTINCT editorial FROM libro");
            rs = ps.executeQuery();

            while (rs.next()) {
                String editorial = rs.getString("editorial");
                editorialesUnicas.add(editorial);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción según tus necesidades (rollback, etc.)
        } finally {
            Conexion.desconectar(); // Cerrar la conexión
        }

        return editorialesUnicas;
    }


}
