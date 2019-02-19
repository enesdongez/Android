package com.enes.notlar.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public final class SqlUtils {
    
    private SqlUtils() {		
    }

    public static int executeUpdateSql(Connection conn, String sql, Object[] sqlParams) {
        int updateCount = 0;
        
        PreparedStatement stmt = null;       
        
        try {        
            // construct the SQL statement
            final String SQL = sql;
            // prepare statement
            stmt = conn.prepareStatement( SQL );
            // bind parameters
            for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
                stmt.setObject( i+1, sqlParams[i] );
            }           
            updateCount = stmt.executeUpdate();            
        }
        catch (Exception _e) {
            System.err.println(_e.fillInStackTrace());
        }        
        finally {            
        }
        return updateCount;
    }        

    public static String nowToDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyyMMdd HHmmss" );
        String now = dateFormat.format(new Date(Calendar.getInstance().getTimeInMillis()));         
        return "TO_DATE('"+now+"','yyyyMMdd HH24miss')";
    }
    
    public static ArrayList<String[]> fetchMultiResults(ResultSet rs) {
    	ArrayList<String[]> resultList = new ArrayList<String[]>();
        try {
        	ResultSetMetaData rsMetaData = rs.getMetaData();
            String[] labels = new String[rsMetaData.getColumnCount()];
            for (int i = 0; i < labels.length; i++) {
                labels[i] = rsMetaData.getColumnLabel(i+1);                     
            }
            resultList.add(labels);
            while (rs.next()) {
                String[] cols = new String[rsMetaData.getColumnCount()];
                for (int i = 0; i < cols.length; i++) {
                    cols[i] = "" + rs.getObject(i+1);
                }
                resultList.add( cols );
            }
        } catch (Exception e) {
        	System.err.println(e.fillInStackTrace());
        }
        
        return resultList;
    }

    public static ArrayList<String[]> queryWithLabels(Connection conn, String sql, Object[] sqlParams) {
        ArrayList<String[]> list = new ArrayList<String[]>();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
      
        try {
            final String SQL = sql;
        
            stmt = conn.prepareStatement( SQL );
        
            // bind parameters
            for (int i=0; sqlParams!=null && i<sqlParams.length; i++ ) {
                stmt.setObject( i+1, sqlParams[i] );
            }
                
            rs = stmt.executeQuery();           
        
            // fetch the results
            list = fetchMultiResults(rs);
        }       
        catch (Exception e) {
        	System.err.println(e.fillInStackTrace());
        }
        finally {           
        }
        
        return list;
    }


    
}

