package PROYECTO;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author demon
 */

import java.util.*;
import java.sql.*;


public class acciones_alumno {
    
    /*
    Esta clase va a representar el manejo de todas las opciones con la
    cual opera el usuario
    */
    
    //crear una clase de conexion
    
    public static Connection getConnection(){
        String url, user, password;
        //donde esta mi bd
        url="jdbc:mysql:3306/localhost/alumnos";
        user="root";
        password="12345";
        
        //objeto de conexion
        Connection con = null;
        try{
            //cuando exista la conexion
            Class.forName("com.mysql.jdbc.Driver");
            //algunas veces no es necesario el puerto, pq ya esta por defecto en el driver
            url="jdbc:mysql://localhost/alumno";
            //enviamos los parametros de la conexion
            con = DriverManager.getConnection(url, user, password);
            
            System.out.println("Si se conecto");
            
        }catch(Exception e){
            System.out.println("No se conecto");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        
        return con;
    }
    
    
    //metodos correspondientes para
    //guardar
    
    public static int Guardar(alumno a){
        int estatus = 0;
        
        try{
        
            //voy a mandar a llamar a mi metodo de conexion
            Connection con = acciones_alumno.getConnection();
            //ahora establezco mi querry
            String q = "insert into Alumnos (nom_alu, pass_alu, email_alu, pais_alu)"
                    + " values (?,?,?,?)";
            //preparamos la sentencia de la querry
            PreparedStatement ps = con.prepareStatement(q);
            //tanto obtener como enviar los parametros gracias al encapsulamiento
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getEmail());
            ps.setString(4, a.getPais());
            
            estatus = ps.executeUpdate();
            con.close();
            System.out.println("Registro exitoso");
            
        }catch(Exception e){
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return estatus;
    }
    
    //editar
    
    public static int Actualizar(alumno a){
        int estatus = 0;
        
        try{
        
            //voy a mandar a llamar a mi metodo de conexion
            Connection con = acciones_alumno.getConnection();
            //ahora establezco mi querry
            String q = "update alumnos set nom_alu = ?,"
                    + " pass_alu = ?,"
                    + " email_alu = ?,"
                    + " pais_alu = ?"
                    + " where id_alu = ?";
            //preparamos la sentencia de la querry
            PreparedStatement ps = con.prepareStatement(q);
            //tanto obtener como enviar los parametros gracias al encapsulamiento
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getEmail());
            ps.setString(4, a.getPais());
            ps.setInt(5, a.getId());
            
            estatus = ps.executeUpdate();
            con.close();
            System.out.println("Aztualizacion exitosa");
            
        }catch(Exception e){
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return estatus;
    }
    
    //eliminar
    public static int Eliminar(int id){
        int estatus = 0;
        
        try{
        
            //voy a mandar a llamar a mi metodo de conexion
            Connection con = acciones_alumno.getConnection();
            //ahora establezco mi querry
            String q = "delete from alumnos where id_alu = ?";
            //preparamos la sentencia de la querry
            PreparedStatement ps = con.prepareStatement(q);
            //tanto obtener como enviar los parametros gracias al encapsulamiento
            ps.setInt(1, id);
            
            estatus = ps.executeUpdate();
            con.close();
            System.out.println("Borrado exitoso");
            
        }catch(Exception e){
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return estatus;
    }
    
    
    //buscar por id
    public static alumno getAlumnoById(int id){
        //objeto de alumno
        alumno a = new alumno();
        
        try{
        
            //voy a mandar a llamar a mi metodo de conexion
            Connection con = acciones_alumno.getConnection();
            //ahora establezco mi querry
            String q = "select * from alumnos where id_alu = ?";
            //preparamos la sentencia de la querry
            PreparedStatement ps = con.prepareStatement(q);
            //tanto obtener como enviar los parametros gracias al encapsulamiento
            ps.setInt(1, id);
            //preparo la consulta
            ResultSet rs = ps.executeQuery();
            
            //buscamos dentro de la tabla
            if(rs.next()){
                a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setPassword(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setPais(rs.getString(5));
            }
            
            con.close();
            System.out.println("Consulta exitoso");
            
        }catch(Exception e){
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return a;
    }
    
    
    //consulta general
    public static List<alumno> getAllAlumnos(){
        //objeto de arraylist para almacenar los alumnos
        List<alumno> lista = new ArrayList<alumno>();
        
        try{
        
            //voy a mandar a llamar a mi metodo de conexion
            Connection con = acciones_alumno.getConnection();
            //ahora establezco mi querry
            String q = "select * from alumnos";
            //preparamos la sentencia de la querry
            PreparedStatement ps = con.prepareStatement(q);
            
            //preparo la consulta
            ResultSet rs = ps.executeQuery();
            
            //obtengo toda la tabla y como no se su dimension
            while(rs.next()){
                //objeto de alumno
                alumno a = new alumno();
                a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setPassword(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setPais(rs.getString(5));
                lista.add(a);
            }
            
            con.close();
            System.out.println("Consulta exitoso");
            
        }catch(Exception e){
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return lista;
    } 
}
