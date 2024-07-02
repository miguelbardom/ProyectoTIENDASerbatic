package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import model.VO.PedidoVO;

public class PedidoDAO {

	public static boolean crearPedido(PedidoVO pedido) {
		Connection con = null;
		PreparedStatement ps = null;
		boolean success = false;

		try {
			con = Conexion.getConexion();
			con.setAutoCommit(false); // Habilitar el modo de transacción explícita

			String sql = "INSERT INTO pedido (usuario_id, fecha, metodopago, numfactura, total, estado) VALUES (?, ?, ?, ?, ?, ?)";

			ps = con.prepareStatement(sql);
			ps.setInt(1, pedido.getUsuario_id());
			ps.setDate(2, pedido.getFecha());
			ps.setString(3, pedido.getMetodopago());
			ps.setString(4, pedido.getNumfactura());
			ps.setDouble(5, pedido.getTotal());
			ps.setString(6, pedido.getEstado());

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

	public static PedidoVO findById(int id) {

		PedidoVO pedido = null;
		Connection con = Conexion.getConexion();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement("SELECT * FROM pedido WHERE id = ?");

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				pedido = new PedidoVO();

				pedido.setId(rs.getInt("id"));
				pedido.setUsuario_id(rs.getInt("usuario_id"));
				pedido.setFecha(rs.getDate("fecha"));
				pedido.setMetodopago(rs.getString("metodopago"));
				pedido.setNumfactura(rs.getString("numfactura"));
				pedido.setTotal(rs.getDouble("total"));
				pedido.setEstado(rs.getString("estado"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		Conexion.desconectar();
		return pedido;

	}
	
	public static List<PedidoVO> getAll() {
		
		ArrayList<PedidoVO> lista = new ArrayList<PedidoVO>();
		PedidoVO pedido = null;
		
		Connection conexion = Conexion.getConexion();
        
        String sql = "SELECT * FROM pedido";
        try (Statement statement = conexion.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
            	pedido = new PedidoVO();
            	pedido.setId(rs.getInt("id"));
				pedido.setUsuario_id(rs.getInt("usuario_id"));
				pedido.setFecha(rs.getDate("fecha"));
				pedido.setMetodopago(rs.getString("metodopago"));
				pedido.setNumfactura(rs.getString("numfactura"));
				pedido.setTotal(rs.getDouble("total"));
				pedido.setEstado(rs.getString("estado"));

                // Agregar el objeto a la lista de resultados
                lista.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Conexion.desconectar();
        return lista;
    }

	public static int obtenerUltimoId() {
		int ultimoId = 0;
		Connection con = Conexion.getConexion();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
//			String sql = "SELECT MAX(id) AS max_id FROM pedido";
			String sql = "SELECT id FROM pedido ORDER BY id DESC LIMIT 1";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				ultimoId = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		Conexion.desconectar();
		return ultimoId;
	}
	
	public static boolean updatePedido(PedidoVO pedido) {
		
		boolean success = false;
		
	    Connection conexion = Conexion.getConexion();
	    String sql = "UPDATE pedido SET fecha=?, metodopago=?, numfactura=?, total=?, estado=? WHERE id=?";
	    
	    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
	    	conexion.setAutoCommit(false);
	    	
	        statement.setDate(1, pedido.getFecha());
	        statement.setString(2, pedido.getMetodopago());
	        statement.setString(3, pedido.getNumfactura());
	        statement.setDouble(4, pedido.getTotal());
	        statement.setString(5, pedido.getEstado());
	        statement.setInt(6, pedido.getId());
	        
	        int rowsUpdated = statement.executeUpdate();
	        System.out.println("Filas actualizadas: " + rowsUpdated); // Depuración
//	        return rowsUpdated > 0;
	        
	        if (rowsUpdated > 0) {
				success = true;
				conexion.commit(); // Confirmar la transacción
			} else {
				conexion.rollback(); // Revertir la transacción si no se realizó la inserción
			}
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	        if (conexion != null) {
				try {
					conexion.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	    }
	    Conexion.desconectar();
	    return success;
	}

	
	public static boolean deletePedido(int id) {
		
		boolean success = false;
		
		Connection conexion = Conexion.getConexion();
        String sql = "DELETE FROM pedido WHERE id=?";
        
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
	
	public static PedidoVO buscarUltimoPedido() {
        PedidoVO ultimoPedido = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = Conexion.getConexion();
            String sql = "SELECT * FROM pedido ORDER BY id DESC LIMIT 1";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                ultimoPedido = new PedidoVO();
                ultimoPedido.setId(rs.getInt("id"));
                ultimoPedido.setUsuario_id(rs.getInt("usuario_id"));
                ultimoPedido.setFecha(rs.getDate("fecha"));
                ultimoPedido.setMetodopago(rs.getString("metodopago"));
                ultimoPedido.setNumfactura(rs.getString("numfactura"));
                ultimoPedido.setTotal(rs.getDouble("total"));
                ultimoPedido.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
			e.printStackTrace();
			
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
        Conexion.desconectar();
        return ultimoPedido;
    }

}
