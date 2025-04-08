/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;
import java.sql.*;
import javax.swing.JOptionPane;


public class Conexion 
{
        String url = "jdbc:mysql://localhost:3306/tiendavirtual";
        String user="root";
        String password="";
       
    public Conexion() {}
       
    public Connection Conectar()
    {
     Connection con=null;
    
       try 
       {
           con = DriverManager.getConnection(url,user,password);
           return con;
           
        }
       catch(SQLException ex)
       {
           JOptionPane.showMessageDialog(null,"ERROR: "+ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
          return null;
       }
       
    }
    
    
                
}
