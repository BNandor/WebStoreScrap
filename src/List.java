import java.util.ArrayList;


public class List<E> {

	public ArrayList<E> list;
	public List() {
		super();
		list = new ArrayList<E>();
	}

	public void add(E e){
		list.add(e);
	}
	public int size(){
		return list.size();
	}
	
	public String toString(){
		String str = new String("x");
		
		for(int i=0;i<list.size()-1;i++){
			str=str+list.get(i)+" : ";
		}
		
		str = str+list.get(list.size()-1)+"]";
		return str;
	}
	
	public E get(int index){
		if(index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}
		return list.get(index);
	}
}
