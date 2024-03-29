/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocio;

import CapaDatos.clsJDBC;
import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author JCarlos
 */
public class clsVenta {
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs=null;
    
    public Integer generarCodigoVenta() throws Exception{
        strSQL = "SELECT COALESCE(max(numventa),0)+1 AS codigo FROM venta;" ;
        try {
            rs=objConectar.consultarBD(strSQL);
            while(rs.next()){
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de venta");
        }
        return 0;
    }
    
    public void registrarVenta (Integer cod, float total, float subtotal, float igv, Boolean tipoComprobante, Integer codCliente, Integer tipoPago ) throws Exception{
        strSQL = "INSERT INTO VENTA VALUES(" +cod+ ", CURRENT_DATE, "+total+" , "+subtotal+" , "+igv+" , "+ tipoComprobante+ " , false , "+ codCliente+" , "+tipoPago +" );";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar la venta");
        }
    }
    
    
}
