package structure;

public class Smartphone extends Goods {

	private String manufacturing_company;
	private Specification spec;
	private String release_date;
	
	public Smartphone(String name, int price, int category, String explanation) {
		super(name, price, category, explanation);
		spec = new Specification();
	}
	
	public void set_manufacturing_compay(String company) { manufacturing_company = company; }
	public void set_spec(String cpu, String display, String battery_size, String RAM, String ROM) {
		spec.cpu = cpu;
		spec.display = display;
		spec.battery_size = battery_size;
		spec.RAM = RAM;
		spec.ROM = ROM;
	}
	public void set_release_date(String release_date) { this.release_date = release_date; }
	
	public String get_manufacturing_company() { return manufacturing_company; }
	public final Specification get_spec() { return spec; }
	public String get_release_date() { return release_date; }
	
	public class Specification {
		public String cpu;
		public String display;
		public String battery_size;
		public String RAM;
		public String ROM;
	}
}
