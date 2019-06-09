package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import back.Set;

@RunWith(Parameterized.class)
public class RegisterAccountTest {

	private String ID;
	private String PW;
	private String name;
	private int birth_date;
	
	private boolean expected;
	private static Set set;
	
	@BeforeClass
	public static void setup() {
		set = new Set();
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		set.DeleteAccount("test_id");
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> getParameters() {
		return Arrays.asList(new Object[][] {
			{"admin", "admin", "°ü¸®ÀÚ", 19000101, false},
			{"test_id", "test_pw", "test_name", 19000101, true},
		});
	}
	
	public RegisterAccountTest(String ID, String PW, String name, int birth_date, boolean expected) {
		this.ID = ID;
		this.PW = PW;
		this.name = name;
		this.birth_date = birth_date;
		this.expected = expected;
	}
	
	@Test
	public void test() {
		boolean result = set.RegisterAccount(ID, PW, name, birth_date);
		assertEquals(expected, result);
	}

}
