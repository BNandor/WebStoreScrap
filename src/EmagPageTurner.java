
public class EmagPageTurner implements NextPage{

	public String next(int i) {
		
		return "/p" + i + "/c";
		
	}

	public String initial() {
		
		return "/c";
	}

}
