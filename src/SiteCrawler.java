import java.io.IOException;
import java.util.ArrayList;


public abstract class SiteCrawler {

	public ExtractorFactory extractorfactory;
	public abstract ArrayList<List<ArrayList<String>>>  Scan() throws IOException;
	
}