import java.io.IOException;
import java.util.ArrayList;

public class Crawler extends SiteCrawler {

	public String[] topics;
	private NextPage turner;
	public Crawler(ExtractorFactory extractor,String [] topics,NextPage turner) {
		super.extractorfactory = extractor;
		this.topics = topics;
		this.turner = turner;
	}

	class ParseTopic extends Thread{
		private Extractor extractor;		
		private String topic;
		ArrayList<List<ArrayList<String>>> result;
		public ParseTopic(String topic,ArrayList<List<ArrayList<String>>> result) {
			super();
			this.topic = topic;
			this.result = result;
			
			
		}
		
		boolean isOver() throws IOException {

			System.out.println("executing " + "" + extractor.EndShell + " "+ extractor.pagename);
			Process getEnd = Runtime.getRuntime().exec(extractor.EndShell + " " + extractor.pagename);

			ProcessInputReader readEnd = new ProcessInputReader(getEnd);
			String pageEnd = readEnd.toString();
			System.out.println("End:" + pageEnd);

			return pageEnd.contains("yes");

		}
		
		public void run() {
			extractor =extractorfactory.getExtractor(); 
			
			try {
				extractor.getPage(topic+turner.initial());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ArrayList<String> names = extractor.extract_names(topic+turner.initial());
			ArrayList<String> prices = extractor.extract_prices(topic+turner.initial());

			List<ArrayList<String>> temp = new List<ArrayList<String>>();
			temp.add(names);
			temp.add(prices);

			try {
				if (isOver()) {
					extractor.removePage(topic+turner.initial());
					return;
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			result.add(temp);

			int j = 2;
			try {
				
				do {
					extractor.removePage(topic+turner.initial());
					extractor.getPage(topic + turner.next(j));
					names = extractor.extract_names(topic + turner.next(j));
					prices = extractor.extract_prices(topic + turner.next(j));

					++j;

					temp = new List<ArrayList<String>>();
					temp.add(names);
					temp.add(prices);
					result.add(temp);

				} while (!isOver());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				extractor.removePage(topic+turner.initial());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public ArrayList<List<ArrayList<String>>> Scan() throws IOException {

		ArrayList<List<ArrayList<String>>> result = new ArrayList<List<ArrayList<String>>>();
		ParseTopic [] parser = new ParseTopic[topics.length];
		
		for (int i = 0; i < topics.length; i++) {
			parser[i] = new ParseTopic(topics[i], result);
		}
		for(int i=0;i<topics.length;i++){
			parser[i].start();
		}
		for(int i=0;i<topics.length;i++){
			try {
				parser[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
