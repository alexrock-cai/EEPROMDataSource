package com.amphenol.agis.jdbc;



import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


import com.amphenol.agis.swing.kit.PathKit;
import com.mchange.v2.c3p0.ComboPooledDataSource;


public class C3p0Pool {
	
	public static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	private static ComboPooledDataSource dataSource;
	
	public C3p0Pool(){
		
	}

	public static Connection getConnection(){
		Connection conn = threadLocal.get();
		//如果当前线程池中没有连接，绑定连接
		if(conn == null){
			if(dataSource == null){
				initDataSource();
			}
			
			try {
				conn = dataSource.getConnection();
				threadLocal.set(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return conn;
	}
	
	public static void closeConnection(){
		Connection conn = threadLocal.get();
		if(conn !=null){
			try {
				conn.close();
				threadLocal.remove();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void initDataSource() {
		// TODO Auto-generated method stub
		String driverClassName;
		String jdbcUrl;
		String user;
		String password;
		int initialPoolSize = 3;
		int maxPoolSize = 15;
		int minPoolSize = 5;
		int acquireRetryDelay=1000;
		int maxIdleTime = 60;
		
		Properties pros = new Properties();
		InputStream is;
		try {
			is = new FileInputStream(PathKit.getRootClassPath()+File.separator+"mysql.properties");
			pros.load(is);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driverClassName=pros.getProperty("driverClassName");
		jdbcUrl = pros.getProperty("jdbcUrl");
		user = pros.getProperty("user");
		password = pros.getProperty("password");
		
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		try {
			cpds.setDriverClass(driverClassName);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cpds.setUser(user);
		cpds.setPassword(password);
		cpds.setJdbcUrl(jdbcUrl);
		cpds.setInitialPoolSize(initialPoolSize);
		cpds.setMaxPoolSize(maxPoolSize);
		cpds.setMinPoolSize(minPoolSize);
		cpds.setAcquireRetryDelay(acquireRetryDelay);
		cpds.setMaxIdleTime(maxIdleTime);
		
		dataSource = cpds;
	}
	
	public static void main(String[] args) throws SQLException{
		initDataSource();
		ComboPooledDataSource ds= dataSource;
		
		System.out.println(ds.getConnection());
	}
}
