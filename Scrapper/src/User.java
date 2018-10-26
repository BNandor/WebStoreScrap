import java.io.IOException;
import java.sql.SQLException;


public class User {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 */
	
	public static void main(String[] args) throws IOException, SQLException {
		
		String[] Emagtopics = { "https://www.emag.ro/telefoane-mobile",
				"https://www.emag.ro/laptopuri","https://www.emag.ro/solid-state_drive_ssd_","https://www.emag.ro/hard_disk-uri" };

		
		DatabaseConnection emagdb = new BasicDatabaseConnection("localhost","EMAG","root","ref   ",3306);
		NewTable newtable = new EmagNewTable(emagdb);
		String table  = "EMAG_"+GetCurrentDate.getdate();
		newtable.newTable(table);
		ExtractorFactory extract  = new ParamteredExtractorFactory("./get-emag-names.sh","./get-emag-prices.sh","./check-emag-end.sh");
		NextPage turner  = new EmagPageTurner();
		SiteCrawler crawler = new Crawler(extract,Emagtopics,turner);
		EmagInserter insert = new EmagInserter(emagdb, crawler.Scan());
		insert.insert("EMAG", table);
		
		
		String[] Evomagtopics = { "https://www.evomag.ro/solutii-mobile-telefoane-mobile",
				"https://www.evomag.ro/portabile-laptopuri","https://www.evomag.ro/componente-pc-solid-state-drive-ssd","https://www.evomag.ro/componente-pc-hard-disk-drive" };

		
		DatabaseConnection evomagdb = new BasicDatabaseConnection("localhost","EVOMAG","root","ref   ",3306);
		newtable = new EmagNewTable(evomagdb);
		table  = "EVOMAG_"+GetCurrentDate.getdate();
		newtable.newTable(table);
		extract  = new ParamteredExtractorFactory("./get-evomag-names.sh","./get-evomag-prices.sh","./check-evomag-end.sh");
		turner = new EvomagTurner();
		crawler = new Crawler(extract,Evomagtopics,turner);
		insert = new EmagInserter(evomagdb, crawler.Scan());
		insert.insert("EVOMAG", table);
		
	}

}

