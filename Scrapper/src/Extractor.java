import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Extractor extends PageExtractor {
	public  String NamesShell;
	public String PricesShell;
	public String EndShell;
	public String pagename;
	
	public Extractor(String NamesShell,String PricesShell,String EndsShell){
		this.NamesShell =NamesShell;
		this.PricesShell =PricesShell;
		this.EndShell =EndsShell;
		
		refreshPagename();
	}
	public void refreshPagename(){
		this.pagename ="temp"+ Thread.currentThread().getId();
	}
	public void getPage(String domain) throws IOException {

		System.out.println("executing " + "wget " + domain + " -O " + pagename);

		Process pGetPage = Runtime.getRuntime().exec(
				"wget " + domain + " -O " + pagename);
		ProcessErrorReader reader = new ProcessErrorReader(pGetPage);
		System.out.println(reader);
	}

	public void removePage(String domain) throws IOException {
		Runtime.getRuntime().exec("rm " + pagename);
	}

	@Override
	public ArrayList<String> extract_names(String domain) {

		String line = null;
		ArrayList<String> result = new ArrayList<String>();
		try {

			// ////////////// //////////////////////

			// /////////// ///////////////////////
			System.out.println("executing " + "" + NamesShell + " " + pagename);
			Process pRunScript = Runtime.getRuntime().exec(
					"" + NamesShell + " " + pagename);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					pRunScript.getInputStream()));
			while ((line = stdInput.readLine()) != null) {
				result.add(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public ArrayList<String> extract_prices(String domain) {

		String line = null;
		ArrayList<String> result = new ArrayList<String>();
		try {

			System.out
					.println("executing " + "" + PricesShell + " " + pagename);
			Process pRunScript = Runtime.getRuntime().exec(
					"" + PricesShell + " " + pagename);

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					pRunScript.getInputStream()));
			while ((line = stdInput.readLine()) != null) {
				result.add(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

}
