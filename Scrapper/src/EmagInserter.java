import java.sql.SQLException;
import java.util.ArrayList;

public class EmagInserter implements DatabaseInserter {
	DatabaseConnection emagdb;
	ArrayList<List<ArrayList<String>>> result;

	public EmagInserter(DatabaseConnection emagdb,
			ArrayList<List<ArrayList<String>>> result) {
		this.emagdb = emagdb;
		this.result = result;
	}

	public void insert(String database, String table) throws SQLException {
		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < result.get(i).get(0).size(); j++) {

				emagdb.query("INSERT INTO `"
						+ database
						+ "`.`"
						+ table
						+ "` (`ID`, `Name`, `Price`, `Extra`, `Currency`) VALUES (NULL, '"
						+ ( (result.get(i)).get(0)).get(j) + "', '" +( (result.get(i)).get(1)).get(j)
						+ "', '" + "color" + "','lei')\n");
			}
		}
	}

}
