import java.sql.SQLException;


public abstract class DatabaseConnection {
	public String ip;
	public String database;
	public String name;
	public String password;
	public int port;
	
	public abstract void query(String sql)throws SQLException;
	
}
