/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy.storage;

import java.sql.*;
import java.util.ArrayList;

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
        Statement stmt  = this.conn.createStatement();
        this.rs   = stmt.executeQuery(query);
        
    }
    public void beginTransaction() {
        String sql = "BEGIN TRANSACTION";
        try {
            Statement stmt  = this.conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void commit() {
        String sql = "COMMIT";
        try {
            Statement stmt  = this.conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void rollBack() {
        String sql = "ROLLBACK";
        try {
            Statement stmt  = this.conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean insert(String table, String[] columns, String[] values) {
        String column = String.join(", ", columns);    
        String value = String.join("', '", values);
        
        String sql = "INSERT INTO " + table + " (" + column + ") VALUES ('" + value + "')";
        boolean success = true;
        try {
            Statement stmt  = this.conn.createStatement();
            stmt.executeUpdate(sql); 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            success = false;
        }
        return success;
    }
    public boolean multipleInsert(String table, String[] columns, ArrayList<String[]> values) {
        String column = String.join(", ", columns);
        String sql = "INSERT INTO " + table + " (" + column + ") VALUES ";
        for(String[] row: values){
            sql+= "('"+ String.join("', '", row)+"'),";
        }
        sql = sql.substring(0, sql.length()-1);
        boolean success = true;
        try {
            Statement stmt  = this.conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            success = false;
            System.out.println(sql);
            System.out.println(e.getMessage());
        }
        return success;
    }
    public boolean deleteWhere(String table, String whereClause) {
        String sql = "DELETE FROM " + table + " WHERE " + whereClause;
        boolean success = true;
        try {
            Statement stmt  = this.conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            success = false;
            System.out.println(e.getMessage());
        }
        
        return success;
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
