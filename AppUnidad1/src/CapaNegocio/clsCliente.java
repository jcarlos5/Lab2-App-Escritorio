/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapaNegocio;

import CapaDatos.clsJDBC;
import java.sql.*;
import java.text.SimpleDateFormat;
/**
 *
 * @author JCarlos
 */
public class clsCliente {
    //Crear instancia de la clase clsJDBC
    clsJDBC objConectar = new clsJDBC();
    String strSQL;
    ResultSet rs;
    
    public ResultSet listarClientes() throws Exception{
        strSQL = "SELECT c.*, t.nombre FROM CLIENTE c INNER JOIN TIPO_CLIENTE t ON c.codtipo = t.codtipo ORDER BY codcliente;";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar Clientes");
        }
    }
    
    public ResultSet filtrarClientes(String cadena) throws Exception{
        strSQL = "SELECT * FROM CLIENTE WHERE dni LIKE '" + cadena + "%' OR ruc LIKE '" + cadena + "%';";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar Clientes");
        }
    }
    
    public ResultSet listarTipoClientes() throws Exception{
        strSQL = "SELECT * FROM TIPO_CLIENTE;";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar Tipos de Cliente");
        }
    }
    
    public Integer generarCodigoCliente() throws Exception{
        strSQL = "SELECT COALESCE(max(codcliente),0)+1 AS codigo FROM cliente;" ;
        try {
            rs=objConectar.consultarBD(strSQL);
            while(rs.next()){
                return rs.getInt("codigo");
            }
        } catch (Exception e) {
            throw new Exception("Error al generar código de cliente");
        }
        return 0;
    }
    
    public void registrar(String cod, String dni, String ruc, String nom, String tel, String correo, String direccion, Boolean vig, int codtipo) throws Exception{
        switch (codtipo) {
            case 1:
                strSQL="INSERT INTO cliente VALUES ("+cod+", '"+ dni+"', null, '"+nom+"', '"+tel+"', '"+correo+"', '"+direccion+"', "+vig+", "+codtipo+");";
                break;
            case 2:
                strSQL="INSERT INTO cliente VALUES ("+cod+", null, '"+ ruc+"', '"+nom+"', '"+tel+"', '"+correo+"', '"+direccion+"', "+vig+", "+codtipo+");";
                break;
            default:
                strSQL="INSERT INTO cliente VALUES ("+cod+", '"+dni+"', '"+ruc+"', '"+nom+"', '"+tel+"', '"+correo+"', '"+direccion+"', "+vig+", "+codtipo+");";
                break;
        }
        
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al registrar el cliente");
        }
    }
    
    public ResultSet buscarCliente(Integer cod) throws Exception{
        strSQL = "SELECT * FROM cliente WHERE codcliente=" + cod + ";";
        try {
            rs=objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al buscar cliente");
        }
    }
    
    public void eliminarCliente(Integer cod) throws Exception {
        strSQL="DELETE FROM cliente WHERE codcliente=" + cod + ";";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al eliminar el cliente");
        }
    }
    
    public void modificarCliente(String cod, String dni, String ruc, String nom, String tel, String correo, String direccion, Boolean vig, int codtipo) throws Exception {
        switch (codtipo) {
            case 1:
                strSQL="UPDATEcliente SET dni='" + dni + "', nombres='" + nom + "', telefono='" + tel + "', correo='" + correo + "', direccion='" + direccion + "', vigencia= " + vig + ", codtipo='" + codtipo + "' WHERE codcliente = " + cod + ";";
                break;
            case 2:
                strSQL="UPDATE cliente SET ruc='" + ruc + "', nombres='" + nom + "', telefono='" + tel + "', correo='" + correo + "', direccion='" + direccion + "', vigencia= " + vig + ", codtipo='" + codtipo + "' WHERE codcliente = " + cod + ";";
                break;
            default:
                strSQL="UPDATE cliente SET dni='" + dni + "', ruc='" + ruc + "', nombres='" + nom + "', telefono='" + tel + "', correo='" + correo + "', direccion='" + direccion + "', vigencia= " + vig + ", codtipo='" + codtipo + "' WHERE codcliente = " + cod + ";";
                break;
        }
        
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al modificar cliente");
        }
    }
    
    public void darDeBajaCliente(Integer cod) throws Exception {
        strSQL="UPDATE cliente SET vigencia = false WHERE codcliente =" + cod + ";";
        try {
            objConectar.ejecutarBD(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al dar de Baja al cliente");
        }
    }
    
    public ResultSet buscarCliente(String doc) throws Exception{
        strSQL = "SELECT c.*, t.nombre FROM (SELECT * FROM cliente WHERE dni = '" + doc + "' OR ruc = '" + doc + "') c INNER JOIN TIPO_CLIENTE t ON c.codtipo = t.codtipo;";
        try {
            rs = objConectar.consultarBD(strSQL);
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al listar Clientes");
        }
    }
}
