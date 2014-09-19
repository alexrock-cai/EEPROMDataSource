package com.amphenol.gis.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.amphenol.agis.jdbc.C3p0Pool;
import com.amphenol.agis.swing.kit.PathKit;



public class SwingTest 
{
	static final int WIGHT=300;
	static final int  HEIGHT=300;
	
	public static void main(String[] args) throws FileNotFoundException, IOException, SQLException
	{
//		JFrame jf= new JFrame("Hello Swing");
//		jf.setSize(WIGHT, HEIGHT);
//		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		jf.setVisible(true);
		//new SwingUserLogin();
		System.out.println(PathKit.getPath(SwingUserLogin.class));
		System.out.println(PathKit.getRootClassPath());
		File file = new File(PathKit.getRootClassPath()+File.separator+"mysql.properties");
		Properties p= new Properties();
		p.load(new FileInputStream(file));
		System.out.println(p.toString());
		
		System.out.println(file.exists());
		System.out.println(file.getAbsolutePath());
		System.out.println(C3p0Pool.getConnection());
		Statement statement=C3p0Pool.getConnection().createStatement();
		String sql="select * from sys_user";
		ResultSet rSet=statement.executeQuery(sql);
		while(rSet.next()){
			System.out.print(rSet.getString("id")+"--");
			System.out.print(rSet.getString("username")+"--");
			System.out.print(rSet.getString("password")+"--");
			System.out.print(rSet.getString("name"));
			System.out.println();
		}
		rSet.close();
		statement.close();
		
	}
}
