import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class BasicDatabaseConnection extends DatabaseConnection {

	private Connection connection=null;
	private String mysqlurl;
	public BasicDatabaseConnection(String ip,String database,String name,String password,int port) throws SQLException {
		this.ip = ip;
		this.database = database;
		this.name = name;
		this.password = password;
		this.port = port;
		
		mysqlurl = "jdbc:mysql://"+ip+":"+port+"/"+database+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"Cannot find the driver in the classpath!", e);
		}
		
		connection = (Connection) DriverManager.getConnection(mysqlurl, name, password);
	}

	@Override
	public void query(String sql) throws SQLException {
		(connection.createStatement()).executeUpdate(sql);
	}

}
