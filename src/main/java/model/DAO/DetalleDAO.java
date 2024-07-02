package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import model.VO.DetalleVO;
import model.VO.PedidoVO;

public class DetalleDAO {
	
	public static boolean crearDetalle(DetalleVO detalle) {
		Connection con = null;
		PreparedStatement ps = null;
		boolean success = false;
		
		try {
			con = Conexion.getConexion();
			con.setAutoCommit(false); // Habilitar el modo de transacción explícita
			
			String sql = "INSERT INTO detalle (pedido_id, libro_id, unidades, preciounidad, impuesto, total) VALUES (?, ?, ?, ?, ?, ?)";
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, detalle.getPedido_id());
			ps.setInt(2, detalle.getLibro_id());
			ps.setInt(3, detalle.getUnidades());
			ps.setDouble(4, detalle.getPreciounidad());
			ps.setDouble(5, detalle.getImpuesto());
			ps.setDouble(6, detalle.getTotal());
			
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
	
	public static boolean deleteDetalle(int idPed) {
		
		boolean success = false;
		
		Connection conexion = Conexion.getConexion();
        String sql = "DELETE FROM detalle WHERE pedido_id=?";
        
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idPed);

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
	
	public static int obtenerUltimoId() {
		int ultimoId = 0;
		Connection con = Conexion.getConexion();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
//			String sql = "SELECT MAX(id) AS max_id FROM detalle";
			String sql = "SELECT id FROM detalle ORDER BY id DESC LIMIT 1";
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
	
	public static List<DetalleVO> getAllByPedidoId(int pedido_id) {
		
		ArrayList<DetalleVO> lista = new ArrayList<DetalleVO>();
		DetalleVO detalle = null;
		
		Connection conexion = Conexion.getConexion();
		PreparedStatement ps = null;
        ResultSet rs = null;
        
		try {
            String sql = "SELECT * FROM detalle WHERE pedido_id = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, pedido_id);
            rs = ps.executeQuery();

            while (rs.next()) {
            	detalle = new DetalleVO();
            	detalle.setId(rs.getInt("id"));
            	detalle.setPedido_id(rs.getInt("pedido_id"));
            	detalle.setLibro_id(rs.getInt("libro_id"));
            	detalle.setUnidades(rs.getInt("unidades"));
    			detalle.setPreciounidad(rs.getDouble("preciounidad"));
    			detalle.setImpuesto(rs.getDouble("impuesto"));
    			detalle.setTotal(rs.getDouble("total"));

                // Agregar el objeto a la lista de resultados
                lista.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		Conexion.desconectar();
        return lista;
    }


}
