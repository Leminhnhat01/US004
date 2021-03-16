package com.project.motorspringmvc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JavaConnectToSQL {
	public static Connection getConnection(String dbURL) {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL);
            System.out.println("connect successfully!");
        } catch(ClassNotFoundException e){
        	e.printStackTrace();
        }
        catch (SQLException ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
	public static void closeConnection(Connection connection) throws SQLException{
		connection.close();
	}

}
