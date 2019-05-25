package com.enes.notlar.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.enes.notlar.sqlite.SqliteClient;

public class NotlarHandler extends AbstractHandler
{
	SqliteClient sqliteClient;

    public NotlarHandler() {
    	sqliteClient = new SqliteClient();
    }
    
    public static Map<String, String> getQueryParameters(HttpServletRequest request) {
        Map<String, String> queryParameters = new HashMap<>();
        String queryString = request.getQueryString();
		try {
			queryString = URLDecoder.decode(queryString, "UTF-8");
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}

        if (queryString == null || queryString.length() == 0) {
            return queryParameters;
        }

        String[] parameters = queryString.split("&");

        for (String parameter : parameters) {
            String[] keyValuePair = parameter.split("=");
            //String[] values = queryParameters.get(keyValuePair[0]);
            //values = ArrayUtils.add(values, keyValuePair.length == 1 ? "" : keyValuePair[1]); //length is one if no value is available.
            //queryParameters.put(keyValuePair[0], values);
            queryParameters.put(keyValuePair[0], keyValuePair[1]);
        }
        return queryParameters;
    }

	@Override
    public void handle( String target,
                        Request baseRequest,
                        HttpServletRequest request,
                        HttpServletResponse response ) throws IOException,
                                                      ServletException
    {
    	//String path = baseRequest.getContextPath();
    	
        response.setContentType("text/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();        
        
        if (target.startsWith("/test")) {
        	sqliteClient.selectAll();
        }
        
        if (target.startsWith("/notlar/kullanicikayit")) {
        	Map<String, String> map = getQueryParameters(request);
        	        	
        	String sql = "INSERT INTO kisiler (name, password ,lastname, isim, date)"
        			+ " values(" 
        			+ "'" + map.get("ad") + "', "
					+ "'" + map.get("sifre") + "', "
					+ "'" + map.get("soyadi") + "', "
					+ "'" + map.get("isim") + "', "
					+ "'" + java.lang.System.currentTimeMillis() + "'"
					+")";
        	sqliteClient.execute(sql);        	
        	
        	
        	String result = "{ \"result\": \"success\" }";
        	out.println(result);
        	System.out.println(target + " -> " + result);
        	
        } else if (target.startsWith("/notlar/kontrol")) {
        	Map<String, String> map = getQueryParameters(request);
        	        	
        	String sql = "select id, name, password ,lastname, isim, date"
        			+ " from kisiler where name = '" + map.get("ad") + "' and password = '" +  map.get("sifre") + "'";
        	
        	String result = "{ \"result\": \"error\" }";
        	
        	try (Connection conn = sqliteClient.connect();
    				Statement stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(sql)) {

       			while (rs.next()) {
    				result = "{"
    						+  "\"ad\": \""+ rs.getString("name") +"\","
							+  "\"sifre\": \""+ rs.getString("password") +"\","
							+  "\"soyad\": \""+ rs.getString("lastname") +"\","
							+  "\"zaman\": \""+ rs.getString("date") +"\","
							+  "\"id\": \""+ rs.getInt("id") +"\""
							+ "}";
    				break;
    			}
    		} catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
        	
        	out.println(result);
        	System.out.println(target + " -> " + result);
        } 
        else if(target.startsWith("/notlar/notlistele")) {
        	Map<String, String> map = getQueryParameters(request);

        	String sql = "select id,baslik,notlar"
        			+ " from notum where user_id = '" + map.get("id") + "'";
        	ArrayList<String> veriler = new ArrayList<String>();
        
         
        	try (Connection conn = sqliteClient.connect();
    				Statement stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(sql)) {
    		
    			while (rs.next()) {
    				veriler.add(0,rs.getString("baslik"));
    				
    			}
    		} catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}        	
        	        	
        	String result = "{ \"veriler\": \""+String.join("#", veriler)+"\" }";
        	out.println(result);
        	System.out.println(target + " -> " + result);
        }
        else if (target.startsWith("/notlar/isimsoyisim")) {
        	Map<String, String> map = getQueryParameters(request);
        	        	
        	String sql = "select isim,lastname"
        			+ " from kisiler where id = '" + map.get("id") + "'";
        	
        	String result = "{ \"result\": \"error\" }";
        	
        	try (Connection conn = sqliteClient.connect();
    				Statement stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(sql)) {
        		while(rs.next()) {
        			result="{"
    						+  "\"ad\": \""+ rs.getString("isim") +"\","
							+  "\"soyad\": \""+ rs.getString("lastname") +"\""+
    						"}";
        			break;
        		}
    			
    		} catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
        	
        	out.println(result);
        	System.out.println(target + " -> " + result);
        }
        else if(target.startsWith("/notlar/notkayit")) {
        	Map<String, String> map = getQueryParameters(request);
        
        	String sql = "INSERT INTO notum (user_id, baslik ,notlar,op)"
        			+ " values(" 
        			+ "'" + map.get("user_id") + "', "
					+ "'" + map.get("baslik") + "', "
				    + "'" + map.get("notlar") + "', "
					+ "'" + map.get("op") + "'"
					+")";
        	sqliteClient.execute(sql);        	
        	
        	
        	String result = "{ \"result\": \"success\" }";
        	out.println(result);
        	System.out.println(target + " -> " + result);
        	
        }  
        else if (target.startsWith("/notlar/kayitkontrol")) {
        	Map<String, String> map = getQueryParameters(request);
      
        	String sql = "select name"
        			+ " from kisiler where name = '" + map.get("ad")+ "'";
        	
        	String result = "{ \"result\": \"error\" }";
        	
        	try (Connection conn = sqliteClient.connect();
    				Statement stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(sql)) {

    			// loop through the result set
    			while (rs.next()) {
    				result = "{ \"adim\": \""+rs.getString("name") +"\""+
    						"}";
				
    				break;
    			}
    		} catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
        	
        	out.println(result);
        	System.out.println(target + " -> " + result);
        }
        else if (target.startsWith("/notlar/baslikyazdir")) {
        	Map<String, String> map = getQueryParameters(request);
        	
        	String sql = "select baslik"
        			+ " from notum where id = '" + map.get("id") + "'";
        	
        	String result = "{ \"result\": \"error\" }";
        	
        	try (Connection conn = sqliteClient.connect();
    				Statement stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(sql)) {
        		while(rs.next()) {
        			result="{"
    						+  "\"baslik\": \""+ rs.getString("baslik") +"\""+
    						"}";
        			break;
        		}
    			
    		} catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
        	
        	out.println(result);
        	System.out.println(target + " -> " + result);
        }
        else if (target.startsWith("/notlar/notyazdir")) {
        	Map<String, String> map = getQueryParameters(request);
        	
        	String sql = "select notlar"
        			+ " from notum where id = '" + map.get("id") + "'";
        	
        	String result = "{ \"result\": \"error\" }";
        	
        	try (Connection conn = sqliteClient.connect();
    				Statement stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(sql)) {
        		while(rs.next()) {
        			result="{"
    						+  "\"notlar\": \""+ rs.getString("notlar") +"\""+
    						"}";
        			break;
        		}
    			
    		} catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
        	
        	out.println(result);
        	System.out.println(target + " -> " + result);
        }
        else if(target.startsWith("/notlar/baslikguncelle")) {
        	Map<String, String> map = getQueryParameters(request);
        
        	String sql = "UPDATE notum SET baslik="+"'"+map.get("baslik")+"'"+" where id="+
        	"'"+map.get("id")+"'";
        			
        	int count = sqliteClient.execute(sql);        	
        	
        	
        	String result = "{ \"result\": \""+count+"\" }";
        	out.println(result);
        	System.out.println(target + " -> " + result);
        	
        }
        else if(target.startsWith("/notlar/notguncelle")) {
        	Map<String, String> map = getQueryParameters(request);
        
        	String sql = "UPDATE notum SET notlar="+"'"+map.get("not")+"'"+" where id="+
        	"'"+map.get("id")+"'";
        			
        	int count = sqliteClient.execute(sql);
        	
        	String result = "{ \"result\": \""+count+"\" }";
        	out.println(result);
        	System.out.println(target + " -> " + result);
        	
        }
       
        else if(target.startsWith("/notlar/alarmkayit")) {
        	 DateFormat df = new SimpleDateFormat("HH:mm:ss / dd:MM:yy");
             Date now = new Date();
        	Map<String, String> map = getQueryParameters(request);
        	        	
        	String sql = "INSERT INTO alarm (alarmkuran,alarmad, alarmtarih )"
        			+ " values(" 
        			+ "'" + map.get("id") + "', "
					+ "'" + map.get("alarmad")+ "', "
					+ "'" + df.format(now) + "' "
					+")";
        	sqliteClient.execute(sql);        	
        	
        	
        	String result = "{ \"result\": \"success\" }";
        	out.println(result);
        	System.out.println(target + " -> " + result);
        	
        }
        else if (target.startsWith("/notlar/notid")) {
        	Map<String, String> map = getQueryParameters(request);
        	
        	String sql = "select id,notlar"
        			+ " from notum where baslik = '" + map.get("baslik") + "'";
        	
        	String result = "{ \"result\": \"error\" }";
        	
        	try (Connection conn = sqliteClient.connect();
    				Statement stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(sql)) {
        		while(rs.next()) {
        			result="{"
    						+  "\"id\": \""+ rs.getString("id") +"\""
							+"}";
        			break;
        		}
    			
    		} catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
        	
        	out.println(result);
        	System.out.println(target + " -> " + result);
        }
        else if (target.startsWith("/notlar/userid")) {
        	Map<String, String> map = getQueryParameters(request);
        	
        	String sql = "select user_id"
        			+ " from notum where id = '" + map.get("id") + "'";
        	
        	String result = "{ \"result\": \"error\" }";
        	
        	try (Connection conn = sqliteClient.connect();
    				Statement stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(sql)) {
        		while(rs.next()) {
        			result="{"
    						+  "\"user_id\": \""+ rs.getString("user_id") +"\""
							+"}";
        			break;
        		}
    			
    		} catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
        	
        	out.println(result);
        	System.out.println(target + " -> " + result);
        }
        else if (target.startsWith("/notlar/kullaniciadigetir")) {
        
        	Map<String, String> map = getQueryParameters(request);
        	        	
        	String sql = "select name"
        			+ " from kisiler where id = '" + map.get("id") + "'";
        	
        	String result = "{ \"result\": \"error\" }";
        	
        	try (Connection conn = sqliteClient.connect();
    				Statement stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(sql)) {
        		while(rs.next()) {
        			result="{"
    						+  "\"name\": \""+ rs.getString("name") +"\""
      						+"}";
        			break;
        		}
    			
    		} catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
        	
        	out.println(result);
        	System.out.println(target + " -> " + result);
        }
        else if (target.startsWith("/notlar/notgetir")) {
        	Map<String, String> map = getQueryParameters(request);
        	        	
        	String sql = "select baslik,notlar"
        			+ " from notum where user_id = '" + Integer.parseInt(map.get("id")) + "'";
        	
        	String result = "{ \"result\": \"error\" }";
        	
        	try (Connection conn = sqliteClient.connect();
    				Statement stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(sql)) {
        		while(rs.next()) {
        			result="{"
    						+  "\"baslik\": \""+ rs.getString("baslik") +"\","
							+  "\"notlar\": \""+ rs.getString("notlar") +"\""+
    						"}";
        			break;
        		}
    			
    		} catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
        	
        	out.println(result);
        	System.out.println(target + " -> " + result);
        }
        else if(target.startsWith("/notlar/alarmlistele")) {
        	Map<String, String> map = getQueryParameters(request);
          

        	String sql = "select id,alarmad,alarmtarih"
        			+ " from alarm where alarmkuran = '" + map.get("id") + "'";
        	ArrayList<String> veriler = new ArrayList<String>();
        
         
        	try (Connection conn = sqliteClient.connect();
    				Statement stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(sql)) {
    		
    			while (rs.next()) {
    				veriler.add(0,rs.getString("alarmad")+" - "+rs.getString("alarmtarih"));
    				
    			}
    		} catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}        	
        	        	
        	String result = "{ \"veriler\": \""+String.join("#", veriler)+"\" }";
        	out.println(result);
        	System.out.println(target + " -> " + result);
        }
        else if (target.startsWith("/notlar/notbaslikkontrol")) {
        	Map<String, String> map = getQueryParameters(request);
      
        	String sql = "select baslik"
        			+ " from notum where baslik = '" + map.get("ad")+ "'";
        	
        	String result = "{ \"result\": \"error\" }";
        	
        	try (Connection conn = sqliteClient.connect();
    				Statement stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(sql)) {

    			// loop through the result set
    			while (rs.next()) {
    				result = "{ \"baslik\": \""+rs.getString("baslik") +"\""+
    						"}";
				
    				break;
    			}
    		} catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
        	
        	out.println(result);
        	System.out.println(target + " -> " + result);
        }
        else if(target.startsWith("/notlar/publicnotlistele")) {
        	Map<String, String> map = getQueryParameters(request);

        	String sql = "select id,baslik,notlar,op"
        			+ " from notum where op = '" + map.get("op") + "'";
        	ArrayList<String> veriler = new ArrayList<String>();
        
         
        	try (Connection conn = sqliteClient.connect();
    				Statement stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(sql)) {
    		
    			while (rs.next()) {
    				veriler.add(0,rs.getString("baslik"));
    				
    			}
    		} catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}        	
        	        	
        	String result = "{ \"veriler\": \""+String.join("#", veriler)+"\" }";
        	out.println(result);
        	System.out.println(target + " -> " + result);
        }
        
        
        else {
        	out.println("<h1>Hello " + target + "</h1>");
        }
        

        baseRequest.setHandled(true);
    }
}