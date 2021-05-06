/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.*;

/**
 *
 * @author ALEX
 */
public class Conexion {
    
    public static Connection conectar(){
        try {
            Connection cn = DriverManager.getConnection("jdbc:ucanaccess://src\\BD\\BD_CBT.accdb;jackcessOpener="
                 + "clases.CryptCodecOpener","sa","DL*2021");
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en conexion local" + e);
        }      
        return (null);
    }
    
}