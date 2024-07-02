package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import model.VO.ProductoVO;

public class ProductoDAO {

	public static List<ProductoVO> findAll() {
		
		ArrayList<ProductoVO> lista = new ArrayList<ProductoVO>();
		ProductoVO producto = null;
		
		Connection con = Conexion.getConexion();
		
		try {
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM producto");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setImpuesto(rs.getDouble("impuesto"));
				producto.setStock(rs.getInt("stock"));
				producto.setBaja(rs.getBoolean("baja"));
				producto.setUrl(rs.getString("url"));
				producto.setCategoria_id(rs.getInt("categoria_id"));
				
				lista.add(producto);
			}
			
			return lista;
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return lista;
		
	}
	
	public static ProductoVO findByID(int id) {
		
		ProductoVO producto = null;
		Connection con = Conexion.getConexion();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM producto WHERE id = ?");
			
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				producto = new ProductoVO();
				
				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setDescripcion(rs.getString("descripcion"));
				producto.setPrecio(rs.getDouble("precio"));
				producto.setImpuesto(rs.getDouble("impuesto"));
				producto.setStock(rs.getInt("stock"));
				producto.setBaja(rs.getBoolean("baja"));
				producto.setUrl(rs.getString("url"));
				producto.setCategoria_id(rs.getInt("categoria_id"));
				
			}
		} catch (SQLException e) {
		    e.printStackTrace();
		    try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
	    }
		
		return producto;		
		
	}
	
	public static boolean restarStock(int id, int cantidad) {
	    Connection con = null;
	    PreparedStatement ps = null;

	    try {
	        con = Conexion.getConexion();
	        
	        // Iniciar una transacción explícita
//	        con.setAutoCommit(false);

	        // Verificar el stock actual del producto
	        ProductoVO producto = findByID(id);
	        if (producto == null) {
	            System.out.println("Producto no encontrado con ID: " + id);
	            return false;
	        }

	        int stockActual = producto.getStock();

	        // Calcular el nuevo stock
	        int nuevoStock = stockActual - cantidad;

	        // Actualizar el stock en la base de datos
	        ps = con.prepareStatement("UPDATE producto SET stock = ? WHERE id = ?");
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


}
