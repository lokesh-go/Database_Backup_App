/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backupdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Lokesh Chandra
 */
public class MsqlConn {
    
        public static final String username = DbBackUp.uname.getText();
        public static final String password = DbBackUp.upass.getText();
        public static String hostname =DbBackUp.hname2.getText();;
        public static String portNo=DbBackUp.port.getText();;
        private static final String CONN_STRING="jdbc:mysql://"+hostname+":"+portNo;
        static Connection conn = null;
        static boolean check = false;
    MsqlConn()
    {
          try
            {
                     conn = DriverManager.getConnection(CONN_STRING,username,password); //Connection Established between netbeans and SQL
                     check = true;
                     System.out.println("Connected Database..3");  
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database is not connected");
            }
    }
    public static void main(String args[])
    {
        new MsqlConn();
    }
}
