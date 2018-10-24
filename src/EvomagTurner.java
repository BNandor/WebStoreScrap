
public class EvomagTurner implements NextPage{

	public String initial() {

		return "/";
	}

	public String next(int i) {
		// TODO Auto-generated method stub
		return "/filtru/pagina:"+i;
	}

}
