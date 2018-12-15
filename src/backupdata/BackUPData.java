/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backupdata;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Lokesh Chandra
 */
public class BackUPData {

    /**
     * @param args the command line arguments
     */
    PreparedStatement pstmt=null;
    ResultSet rs = null;
    static ArrayList<String> mylist = new ArrayList<String>();
    
    BackUPData()
    {
        try {
            MsqlConn msql = new MsqlConn();
            if(msql.check)
            {
            DbBackUp dbb = new DbBackUp();
            
            
            
            
            String sql = "show databases";
            pstmt = msql.conn.prepareStatement(sql);
            
            rs = pstmt.executeQuery();
            
            while(rs.next())
            {
              //  System.out.println(rs.getString("Database"));
              //  dbb.dblist.addItem(rs.getString("Database"));
                mylist.add(rs.getString("Database"));
            }
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(BackUPData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        new BackUPData();
    }
    
}
