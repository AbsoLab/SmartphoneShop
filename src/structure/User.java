package structure;

public class User {

	private String name;
	private String ID;
	private String PW;
	private int birth_date;
	
	public User(String ID, String PW, String name, int birth_date) {
		
		this.name = name;
		this.ID = ID;
		this.PW = PW;
		this.birth_date = birth_date;
	}
	
	public User(String ID, String PW, String name, String birth_date ) {
		
		this.name = name;
		this.ID = ID;
		this.PW = PW;
		this.birth_date = Integer.valueOf(birth_date);
	}
	
	public boolean ChangePW(String prePW, String newPW) {
		
		return false;
	}
	
	public String get_ID() { return ID; }
	public String get_PW() { return PW; }
	public String get_name() { return name; }
	public int  get_birth_date() { return birth_date; }
}
