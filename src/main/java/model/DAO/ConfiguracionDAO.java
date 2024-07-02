package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import model.VO.ConfiguracionVO;

public class ConfiguracionDAO {
	
	public static ConfiguracionVO findAll() {
		ConfiguracionVO config = null;
        Connection con = Conexion.getConexion();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM libro WHERE baja = false");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                config = new ConfiguracionVO();
                
                config.setId(rs.getInt("id"));
                config.setClave(rs.getString("clave"));
                config.setValor(rs.getString("valor"));
                config.setTipo(rs.getString("tipo"));

            }

            return config;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        Conexion.desconectar();
        return config;
    }
	
	public static ConfiguracionVO getConfigByClave(String clave) {
        
		ConfiguracionVO config = null;
        Connection conexion = Conexion.getConexion();
        
        try {
            if (conexion != null) {
                String query = "SELECT * FROM configuracion WHERE clave = ?";
                
                PreparedStatement ps = conexion.prepareStatement(query);
                ps.setString(1, clave);
                
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String clave1 = rs.getString("clave");
                    String valor = rs.getString("valor");
                    String tipo = rs.getString("tipo");
                    
                    config = new ConfiguracionVO();
                    config.setId(id);
                    config.setClave(clave1);
                    config.setValor(valor);
                    config.setTipo(tipo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        Conexion.desconectar();
        return config;
    }
	
	public static boolean updateFacturaId(int nuevoNumeroFactura) {
        boolean success = false;
        Connection conexion = Conexion.getConexion();
        String query = "UPDATE configuracion SET valor = ? WHERE clave = 'factura_id'";
        
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
        		conexion.setAutoCommit(false);
                
                statement.setString(1, String.valueOf(nuevoNumeroFactura));
                
                int rowsUpdated = statement.executeUpdate();
    	        System.out.println("Filas actualizadas: " + rowsUpdated); // Depuración
//    	        return rowsUpdated > 0;
    	        
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
	
	public static boolean updateConfig(ConfiguracionVO config, String clave) {
		
		boolean success = false;
		
		Connection conexion = Conexion.getConexion();
        String sql = "UPDATE configuracion SET valor=?, tipo=? WHERE clave=?";
        
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
        	
        	ConfiguracionVO configSt = config;
            statement.setString(1, configSt.getValor());
            statement.setString(2, configSt.getTipo());
            statement.setString(3, configSt.getClave());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
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

}
