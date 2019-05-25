package com.enes.notlar.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SqliteClient {
	
	private Connection conn = null;

	public Connection connect() throws SQLException  {
		// SQLite connection string		
		if (conn == null || conn.isClosed() == true) {
			try {
				 // load the sqlite-JDBC driver using the current class loader
			    //Class.forName("org.sqlite.JDBC");
			    
				String url = "jdbc:sqlite:db/notlar.db";
				conn = DriverManager.getConnection(url);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return conn;
	}

	public void selectAll() {
		String sql = "select * from kisiler";

		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int execute(String sql) {
		int count = 0;
		try { 
			Connection conn = this.connect();
			Statement stmt = conn.createStatement();			
			count = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return count;
	}
	/*
	public ArrayList<HashMap<String,String>> query(String sql) {		
		ArrayList<HashMap<String,String>> rows = new ArrayList<HashMap<String,String>>();		

		try (Connection conn = this.connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// loop through the result set
			while (rs.next()) {
				HashMap<String,String> map = new HashMap<String, String>();
				rs.get
				System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t");
				rs.getObject(columnIndex)
				rows.add(map);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return rows;
	}
	*/
	
	/*
	try { 
		String sql = "INSERT INTO kisiler (name, password ,lastname, isim, date)"
    			+ " values(?,?,?,?,?)";
		
		Connection conn = sqliteClient.connect();
		PreparedStatement  stmt = conn.prepareStatement(sql);
		stmt.setString(1, map.get("ad"));
		stmt.setString(2, map.get("sifre"));
		stmt.setString(3, map.get("soyadi"));
		stmt.setString(4, map.get("isim"));
		stmt.setString(5, ""+java.lang.System.currentTimeMillis());
		
		stmt.executeUpdate(sql);
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
	*/

}
