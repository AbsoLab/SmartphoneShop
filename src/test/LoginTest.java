package test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import back.Set;

@RunWith(Parameterized.class)
public class LoginTest {

	private String ID;
	private String PW;
	private boolean expected;
	private static Set set;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		set = new Set();
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> getParameters() {
		return Arrays.asList(new Object[][] {
			{"admin", "admin", true},
			{"test_id1", "test_pw1", false},
		});
	}
	
	public LoginTest(String ID, String PW, boolean expected) {
		this.ID = ID;
		this.PW = PW;
		this.expected = expected;
	}
	
	@Test
	public void test() {
		boolean result = set.Login(ID, PW);
		assertEquals(expected, result);
	}

}
