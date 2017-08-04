package baseutils;

import java.io.IOException;
import java.io.InputStream;
/**
 * 打开/关闭数据库
 */
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import com.mysql.jdbc.Statement;

import framework.BasePage;
import framework.MyUserInfo; 

public class JdbcObject {
	    //驱动程序名
	    private final String DRIVER = "com.mysql.jdbc.Driver";  
	    //数据库地址
	    private  String dbip;
	    private  String user;
	    private  String password;
	    private   Connection conn = null;  
	    public  PreparedStatement pst = null;  
	    private  Session session ;
	    
	    
	    public JdbcObject() {  
	        try {  
	        	Properties prop=new Properties();
	    		InputStream in=JdbcObject.class.getClassLoader().getResourceAsStream("testinfo.properties");
	    		prop.load(in);
	    		user=prop.getProperty("dbname");
	    		password=prop.getProperty("dbpassword");
	    		dbip=prop.getProperty("dbip");
	    		in.close();
	    		System.out.println("用户名为:"+user+" 数据库密码为:"+password+" 服务器IP为:"+dbip);
	        	
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
	  
	    
	    /*
	     * 获取SSH通道连接
	     */
		public  void go() {
			String identifyFile = "D:\\ph\\penghong_rsa";
			String user ="ubuntu";
			String password = "ph2017@icx";//SSH连接密码
			String host = "119.29.176.181";//SSH服务器
			int port = 22;//SSH访问端口
			try {
				JSch jsch = new JSch();
				jsch.addIdentity(identifyFile);
				session = jsch.getSession(user, host, port);
				UserInfo userInfo = new MyUserInfo(password);
				session.setUserInfo(userInfo);
				session.connect();
				System.out.println(session.getServerVersion());//这里打印SSH服务器版本信息
				System.out.println("ip为:"+dbip);
		        //int assinged_port = session.setPortForwardingL("10.0.5.206",5555, "10.104.103.7", 3306);//端口映射 转发  
				int assinged_port = session.setPortForwardingL("10.0.10.37",5555, dbip, 3306);//端口映射 转发  
		        System.out.println("localhost:" + assinged_port);  
		        

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		    finally
		    {
		        session.disconnect();
		        }
		        
		}
		
	
		/*
		 * 获取与数据库的连接
		 */
		public void getConection()
		{
		 
	    	try {
				Class.forName("com.mysql.jdbc.Driver");
				conn =DriverManager.getConnection("jdbc:mysql://10.0.10.37:5555/tanyun", user, password);//获取连接
		        
		        System.out.println("连接成功");
		            
			} catch (Exception e) {
			e.printStackTrace();
			}//指定连接类型 
	    	finally{
	    		try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	    	}
			
		}
		




		public static void main(String args[])
	    {     
	    	JdbcObject db=new JdbcObject();
	    	db.go();
	    	db.getConection();

	    	  
	    	
	    }

}
