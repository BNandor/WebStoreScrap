import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ProcessInputReader {

	private Process prc;

	public ProcessInputReader(Process prc) {
		super();
		this.prc = prc;
	}
	
	public void readAll() throws IOException{
		String line;
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(
				prc.getInputStream()));
		while ((line = stdInput.readLine()) != null) {
			
			System.out.println(line);
		}
	}
	public String toString(){
		String str="";
		String line;
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(
				prc.getInputStream()));
		try {
			while ((line = stdInput.readLine()) != null) {
				
				str=str+line+"\n";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
}
