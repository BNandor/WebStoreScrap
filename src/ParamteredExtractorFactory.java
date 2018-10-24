
public class ParamteredExtractorFactory implements ExtractorFactory {
	public String names;
	public String prices;
	public String end;
	
	
	public ParamteredExtractorFactory(String names, String prices, String end) {
		super();
		this.names = names;
		this.prices = prices;
		this.end = end;
	}


	public Extractor getExtractor() {

		return new Extractor(names,prices,end);
	}

}
