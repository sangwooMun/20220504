package co.micol.notice.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource { // DAO 객체
	private static DataSource dataSource = new DataSource();	// 자기 자신 인스턴스
	private DataSource() {};	// 외부에서 사용하지 못하게 하고
	
	private Connection conn;
	private String driver;
	private String url;
	private String user;
	private String password;
	
	public static DataSource getInstance() {	// 불러주고
		return dataSource;
	}
	
	public Connection getConnection() {
		dbConfig();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	private void dbConfig() {
		Properties properties = new Properties();
		String resource = getClass().getResource("/db.properties").getPath();
		try {
			properties.load(new FileInputStream(resource));
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
