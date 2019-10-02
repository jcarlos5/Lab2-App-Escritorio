/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocio;

import CapaDatos.clsJDBC;
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
}
