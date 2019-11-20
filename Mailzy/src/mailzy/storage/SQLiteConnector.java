/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy.storage;

import java.sql.*;

/**
 *
 * @author lalexandrov
 */
public class SQLiteConnector {
    public SQLiteConnector() throws SQLException {
        // db parameters
        String url = "jdbc:sqlite:"+System.getProperty("user.dir")+"/maildb.db";
        // create a connection to the database
        this.conn = DriverManager.getConnection(url);
    }
    
    public void query(String query) throws SQLException {
        Statement stmt  = conn.createStatement();
        this.rs   = stmt.executeQuery(query);
        
    }
    public ResultSet fetch() {
        ResultSet tmp = this.rs;
        this.rs = null;
        
        return tmp;
    }
    @Override
    protected void finalize() {
         try {
            if (this.conn != null) {
                this.conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private Connection conn = null;
    private ResultSet rs = null;
}
