import java.io.IOException;
import java.util.ArrayList;


public abstract class PageExtractor {
	public String pagename;
	public abstract ArrayList<String>  extract_names(String domain);
	public abstract ArrayList<String>  extract_prices(String domain);
	public abstract void getPage(String domain) throws IOException;
	public abstract void removePage(String domain) throws IOException;
}
