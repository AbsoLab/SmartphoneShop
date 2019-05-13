package structure;

public class Goods {

	protected String name;
	protected int price;
	protected int count;
	protected boolean smartphone;
	protected int goods_id;
	
	protected String explanation;
	
	public Goods(String name, int price, int count, boolean smartphone, int goods_id, String explanation) {
		
		this.name = name;
		this.price = price;
		this.count = count;
		this.smartphone = smartphone;
		this.goods_id = goods_id;
		this.explanation = explanation;
	}
	
	public String get_name() { return name; }
	public int get_price() { return price; }
	public int get_count() { return count; }
	public boolean is_smartphone() { return smartphone; }
	public int get_goods_id() { return goods_id; }	
	public String get_explanation = explanation;
}
