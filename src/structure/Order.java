package structure;

public class Order {

	private String name;
	private String address;
	private String phone_num;
	private String card_corporation;
	private String card_num;
	private int total_price;
	
	public Order(String name, String address, String phone_num, String card_corporation, String card_num, int total_price) {
		this.name = name;
		this.address = address;
		this.phone_num = phone_num;
		this.card_corporation = card_corporation;
		this.card_num = card_num;
		this.total_price = total_price;
	}
	
	public String get_name() {return name;}
	public String get_address() {return address;}
	public String get_phone_num() {return phone_num;}
	public String get_card_corporation() {return card_corporation; }
	public String get_card_num() {return card_num;}
	public int get_total_price() {return total_price;}
}
