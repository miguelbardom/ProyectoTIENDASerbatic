package model.VO;

import java.sql.Date;
import java.text.DecimalFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PedidoVO {
	
	int id;
	int usuario_id;
	Date fecha;
	String metodopago;
	String numfactura;
	Double total;
	String estado;
	/*
	public PedidoVO(int usuario_id, Date fecha, String metodopago, Double total, String estado) {
//        this.id = id;
        this.usuario_id = usuario_id;
        this.fecha = fecha;
        this.numfactura = generarNumeroFactura();
        this.metodopago = metodopago;
        this.total = total;
        this.estado = estado;
    }*/
    
    private String generarNumeroFactura() {
        DecimalFormat df = new DecimalFormat("0000");
        String formattedId = df.format(id);
        
        return "FA-" + formattedId;
    }
	
}
