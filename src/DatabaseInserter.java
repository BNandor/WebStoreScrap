import java.sql.SQLException;


public interface DatabaseInserter {
	public abstract void insert(String database,String table)throws SQLException;
}
