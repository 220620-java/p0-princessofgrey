package list_types;

public interface List<T> {
	
	public void add (T t);
	
	
	
	public void add(T t, int index);
	public void addAll(T...t);
	public T get(int index);
	public int indexOf(T t);
	
	

}
