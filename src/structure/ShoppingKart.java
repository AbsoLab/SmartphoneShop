package structure;

public class ShoppingKart {
	
	private String name;
	private int count;
	private int price;

	public ShoppingKart(String name, int count, int price) {
		this.name = name;
		this.count = count;
		this.price = price;
	}
	
	public String get_name() { return name; }
	public int get_count() { return count;}
	public int get_price() { return price; }
	
	public void set_count(int count) { this.count = count; }
}
