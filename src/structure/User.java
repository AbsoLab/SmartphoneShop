package structure;

public class User {

	private String name;
	private String ID;
	private String PW;
	private int birth_date;
	private int pid;
	
	public User(String ID, String PW, String name, int birth_date, int pid) {
		
		this.name = name;
		this.ID = ID;
		this.PW = PW;
		this.birth_date = birth_date;
		this.pid = pid;
	}
	
	public User(String ID, String PW, String name, String birth_date, int pid ) {
		
		this.name = name;
		this.ID = ID;
		this.PW = PW;
		this.birth_date = Integer.valueOf(birth_date);
		this.pid = pid;
	}
	
	public boolean ChangePW(String prePW, String newPW) {
		
		return false;
	}
	
	public String get_ID() { return ID; }
	public String get_PW() { return PW; }
	public String get_name() { return name; }
	public int  get_birth_date() { return birth_date; }
	public int get_pid() { return pid; }
}
