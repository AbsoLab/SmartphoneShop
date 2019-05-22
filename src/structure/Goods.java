package structure;

public class Goods {

	protected String name;
	protected int price;
	protected int category;
	protected String explanation;
	
	public Goods(String name, int price, int category, String explanation) {
		
		this.name = name;
		this.price = price;
		this.category = category;
		this.explanation = explanation;
	}
	
	public String get_name() { return name; }
	public int get_price() { return price; }
	public int get_category() { return category; }
	public String get_explanation() { return explanation; }
}
