/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class marks {
   
    public static void main(String[] args) {
         String a[] = new String[5];
                   for(int i=0; i<4; i++)
                   {
                       a[i] = "" + i;
                   }    
                   
                    ArrayList al = new  ArrayList();
                al.add("C");
                al.add("A");
                al.add("E");
                al.add("B");
                al.add("D");
                al.add("F");
                //al.add(1, "A2");
                   
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                }
                
                catch(Exception exp)
                {
                    System.out.println("Please check the driver.");
                }
            
                try
                {
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
                    Statement stmt = con.createStatement();
                    //stmt.setBlob(1,  new SerialBlob(a));
                    stmt.executeUpdate("INSERT INTO array VALUES(3,'"+al+"')");
                }   
            
                catch(Exception exp)
                {
                    System.out.println(exp);
                }

               }
    }
    

