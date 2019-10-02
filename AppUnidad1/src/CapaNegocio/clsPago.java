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
 * @author laboratorio_computo
 */
public class clsPago {
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs=null;
    
    public Integer generarCodigoPago() throws Exception{
        strSQL = "SELECT COALESCE(max(numventa),0)+1 AS codigo FROM pago;" ;
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
    
    public void registrarPago(Integer codPago,Integer codTipoPago, float montoIngresado, float vuelto, Integer codVenta, Boolean estadoPago) throws Exception{
        strSQL = "INSERT INTO pago VALUES ("+codPago+", "+montoIngresado+" , "+vuelto+" , "+codTipoPago+" , "+codVenta +" , true );";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar el pago de la venta");
        }
    }
    
    public void generarPago(Integer codVenta, Integer codTipoPago, Integer numCuotas, Integer tipoCuota) throws Exception{
        strSQL = "INSERT INTO pago VALUES ("+codPago+", null , null , "+codTipoPago+" , "+codVenta +" , false );";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar el pago de la venta");
        }
    }
    
}
