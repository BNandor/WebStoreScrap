import java.sql.SQLException;


public class EmagNewTable implements NewTable{
	DatabaseConnection emagdb;
	public EmagNewTable(DatabaseConnection emagdb) {
		this.emagdb = emagdb;
	}
	public void newTable(String name) {
		try {
			emagdb.query("CREATE TABLE `"+name+"` (ID int PRIMARY KEY AUTO_INCREMENT, Name text, Price int(11), Extra text, Currency text);");
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
}
