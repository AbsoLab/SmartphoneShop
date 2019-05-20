package structure;

public class Goods {

	protected String name;
	protected int price;
	protected boolean smartphone;
	
	protected String explanation;
	
	public Goods(String name, int price, boolean smartphone, String explanation) {
		
		this.name = name;
		this.price = price;
		this.smartphone = smartphone;
		this.explanation = explanation;
	}
	
	public String get_name() { return name; }
	public int get_price() { return price; }
	public boolean is_smartphone() { return smartphone; }
	public String get_explanation() { return explanation; }
}
