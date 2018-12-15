/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backupdata;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

/**
 *
 * @author Lokesh Chandra
 */
public class QueryWork {
    
    Connection conn2 = null;
    static ArrayList<String> mylist =null; 
    QueryWork(String dbname) throws IOException
    {
        StringBuilder strBuilder=null;
        try {
            mylist=new ArrayList<String>();
            String CONN_STRING="jdbc:mysql://"+MsqlConn.hostname+":"+MsqlConn.portNo+"/"+dbname;
            conn2 =  DriverManager.getConnection(CONN_STRING,MsqlConn.username,MsqlConn.password);
            Statement stmt = conn2.createStatement();
            
            DatabaseMetaData dbmd = conn2.getMetaData();
            String[] types = {"TABLE"};
            ResultSet rs = dbmd.getTables(null,null, "%", types);
            
            while(rs.next())
            {
                mylist.add(rs.getString("TABLE_NAME"));
            }
           
            if(!(mylist.isEmpty()))
            {
             strBuilder=new StringBuilder();
            
        //    StringBuilder tempbuild2 = new StringBuilder();
            for(int i=0;i<mylist.size();i++)
            {
                String currTableName = mylist.get(i);
                String qr ="show create table "+currTableName;
                ResultSet rs2 = stmt.executeQuery(qr);
                
                while(rs2.next())
                {
                    String res = rs2.getString(2);
                //    System.out.println(res);
                    strBuilder.append(res);
                    strBuilder.append("\n\n");
              //  System.out.println();
                }
                
                PreparedStatement pstmt = conn2.prepareStatement("select * from "+currTableName);
                ResultSet rs3= pstmt.executeQuery();
                ResultSetMetaData meta = rs3.getMetaData();
   
                List<String> columns = new ArrayList<>();
                for(int j=1;j<=meta.getColumnCount();j++)
                        columns.add(meta.getColumnName(j));
                
                String ins = "insert into "+currTableName+" ("+columns.stream().collect(Collectors.joining(", "))+ ") values ("+columns.stream().map(c -> "?").collect(Collectors.joining(", "))+");"+"\n";
            
                PreparedStatement pstmt2 = conn2.prepareStatement(ins);
               
                while(rs3.next())
                {
                    for(int k=1;k<=meta.getColumnCount();k++)
                    {
                        pstmt2.setObject(k,rs3.getObject(k));
                    }
                        StringBuilder tempbuild = new StringBuilder();
                        tempbuild.append(pstmt2);
                        tempbuild.delete(0,48);
                    
                     //   System.out.println(tempbuild);
                    //    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
                  //      tempbuild2.append(tempbuild);
                        
                    strBuilder.append(tempbuild);
                }
                strBuilder.append("\n\n");
               
        }
            }
           
        //    System.out.println(strBuilder);
        DbBackUp dbu = new DbBackUp();
        String p =DbBackUp.pa;
        String path = p+dbname+".sql";
     //   System.out.println(path);
        BufferedWriter bw  = new BufferedWriter(new FileWriter(path));
        bw.write(strBuilder.toString());
        bw.close();
        JOptionPane.showMessageDialog(null, "Created...."+path);
        System.out.println("sucesss");
        } catch (SQLException ex) {
            Logger.getLogger(QueryWork.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Your Database is Empty.....!!!!!");
        }
    }
}
